package com.example.jetpack_compose_all_in_one.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.SimpleIconButton
import com.example.jetpack_compose_all_in_one.SwitchRow
import com.example.jetpack_compose_all_in_one.alarm.database.AlarmInfo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.reflect.KFunction1

@Composable
fun AlarmMainUI(viewModel: AlarmViewModel = hiltViewModel()) {
    val localContext = LocalContext.current

    val response by viewModel.alarmTime.observeAsState()

    SimpleIconButton(R.drawable.baseline_alarm_add_24) {
        setAlarm(context = localContext, viewModel::setAlarms)
    }
    SimpleIconButton(R.drawable.baseline_auto_delete_24) {
        response?.let{ cancelAlarm(localContext, it, viewModel::removeAlarm) }
    }
    /*SimpleTextButton(buttonMessage = stringResource(id = R.string.set_alarm)) {
        setAlarm(context = localContext, viewModel::setAlarms)
    }*/

    var selected2 by remember { mutableStateOf(true) }
    response?.let {
        SwitchRow(name = "$it", change = selected2, onCheckedChange = { on ->
            selected2 = on
            if (!on) cancelAlarm(localContext, it, viewModel::removeAlarm)
        })
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


