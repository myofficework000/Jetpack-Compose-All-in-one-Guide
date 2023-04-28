package com.example.jetpack_compose_all_in_one.features.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.alarm.database.AlarmInfo
import com.example.jetpack_compose_all_in_one.ui.components.IconTextButton
import com.example.jetpack_compose_all_in_one.utils.formatTime
import com.example.jetpack_compose_all_in_one.utils.getTime
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale
import kotlin.reflect.KFunction1
import kotlin.reflect.KFunction2

@Composable
fun AlarmMainUI(
    viewModel: AlarmViewModel = hiltViewModel(),
    snackbarFunc: (String, Boolean) -> Unit
) {
    val localContext = LocalContext.current

    val response by viewModel.alarmTime.observeAsState()
    //var selected2 by remember { mutableStateOf(true) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconTextButton(R.drawable.baseline_alarm_add_24,"Add Alarm") {
                setAlarm(context = localContext, viewModel::setAlarms)
            }
            IconTextButton(R.drawable.baseline_auto_delete_24, "Clear All Alarms") {
                response?.let{ cancelAlarm(localContext, it, viewModel::removeAlarm) }
            }
        }

        response?.let {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                items(it) {data ->
                    AlarmItem(
                        data,
                        { on -> toggleAlarm(
                            localContext,
                            data,
                            on,
                            { info, delta -> snackbarFunc("Alarm ${info.id} rings after ${delta.formatTime()}", false) },
                            { info -> snackbarFunc("Alarm ${info.id} disabled.", false) },
                            viewModel::toggleAlarm
                        ) },
                        { viewModel.removeAlarm(data) }
                    )
                }
            }

            /*SwitchRow(name = "$it", change = selected2, onCheckedChange = { on ->
                selected2 = on
                if (!on) cancelAlarm(localContext, it, viewModel::removeAlarm)
            })*/
        }
    }
}

fun setAlarm(
    context: Context,
    setAlarmFunc: KFunction1<AlarmInfo, Int>
) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR)
    val minute = calendar.get(Calendar.MINUTE)
    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            setAlarmManager(context, selectedHour, selectedMinute, setAlarmFunc)
        }, hour, minute, true
    )
    timePicker.show()
}

fun toggleAlarm(
    context: Context,
    alarmInfo: AlarmInfo,
    willEnable: Boolean, // If true, it's turning the alarm back on
    displayTimeLeftFunc: (AlarmInfo, Long) -> Unit,
    displayAlarmDisabledFunc: (AlarmInfo) -> Unit,
    toggleAlarmFunc: KFunction2<AlarmInfo, Boolean, Unit>
) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        alarmInfo.id,
        Intent(context, AlarmBroadcastReceiver::class.java),
        PendingIntent.FLAG_IMMUTABLE
    )

    if (willEnable) {
        val timeNow = LocalDateTime.now()
        val timeTarget = LocalTime.of(alarmInfo.hour, alarmInfo.minute)
        val ringTime = LocalDateTime.of(
            timeNow.toLocalDate().plusDays( if (timeNow.toLocalTime() > timeTarget) 1 else 0 ),
            timeTarget
        )
        val millisDelta = ChronoUnit.MILLIS.between(timeNow, ringTime)
        alarmManager.setRepeating(
            AlarmManager.RTC,
            millisDelta,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        displayTimeLeftFunc(alarmInfo, millisDelta)
    } else {
        alarmManager.cancel(pendingIntent)
        displayAlarmDisabledFunc(alarmInfo)
    }

    toggleAlarmFunc(alarmInfo, willEnable)
}

fun cancelAlarm(
    context: Context,
    alarms: List<AlarmInfo>,
    removeAlarmFunc: KFunction1<AlarmInfo, Unit>
) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmBroadcastReceiver::class.java)

    alarms.forEach {
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            it.id,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
        removeAlarmFunc(it)
    }
}

fun getTime(timeInMillis: Long) {
    val formatter = SimpleDateFormat("dd:HH:mm:ss", Locale.UK)

    val date = Date(timeInMillis)
    formatter.format(date) ?: "No time set"
}

fun setAlarmManager(
    context: Context,
    hour: Int,
    minute: Int,
    setAlarmFunc: KFunction1<AlarmInfo, Int>
) {
    val alarmId = setAlarmFunc( AlarmInfo(0, hour, minute, true ) )

    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmBroadcastReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        alarmId,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minute)
    alarmManager.setRepeating(
        AlarmManager.RTC,
        calendar.timeInMillis,
        AlarmManager.INTERVAL_DAY,
        pendingIntent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlarmItem(
    data: AlarmInfo,
    toggleAlarmFunc: (Boolean) -> Unit,
    deleteAlarmFunc: () -> Unit
) {
    var isOn by remember{ mutableStateOf(data.enabled) }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .combinedClickable( onLongClick = { deleteAlarmFunc() } ){},
        contentAlignment = Alignment.CenterStart
    ) {
        Text(data.getTime())
        Switch(
            checked = isOn,
            onCheckedChange = { toggleAlarmFunc(it); isOn = !isOn },
            modifier = Modifier.align(Alignment.CenterEnd),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Red,
                uncheckedThumbColor = Color.Green,
                checkedTrackColor = Color.Yellow,
                uncheckedTrackColor = Color.Black
            )
        )
    }
}
