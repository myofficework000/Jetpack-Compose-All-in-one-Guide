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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.SimpleTextButton
import com.example.jetpack_compose_all_in_one.SwitchRow
import com.example.jetpack_compose_all_in_one.alarm.database.AlarmInfo
import com.example.jetpack_compose_all_in_one.utils.Constants.ALARM_REQUEST_CODE
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AlarmMainUI(viewModel: AlarmViewModel = hiltViewModel()) {
    val localContext = LocalContext.current

    val response = viewModel.alarmTime.observeAsState()

    SimpleTextButton(buttonMessage = stringResource(id = R.string.set_alarm)) {
        setAlarm(context = localContext)
    }

    var selected2 by remember { mutableStateOf(true) }
    response.value?.let {
        SwitchRow(name = "$it", change = selected2, onCheckedChange = {
            selected2 = it
            cancelAlarm(localContext)
        })
    }
}

fun setAlarm(context: Context) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR)
    val minute = calendar.get(Calendar.MINUTE)
    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            setAlarmManager(context, selectedHour, selectedMinute)
        }, hour, minute, true
    )
    timePicker.show()
}

fun cancelAlarm(context: Context) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmBroadcastReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        ALARM_REQUEST_CODE,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )
    alarmManager.cancel(pendingIntent)
}

fun getTime(timeInMillis: Long) {
    val formatter = SimpleDateFormat("dd:HH:mm:ss", Locale.UK)

    val date = Date(timeInMillis)
    formatter.format(date) ?: "No time set"
}

fun setAlarmManager(context: Context, hour: Int, minute: Int) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmBroadcastReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        ALARM_REQUEST_CODE,
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

    val alarmInfo = AlarmInfo(
        ALARM_REQUEST_CODE,
        hour,
        minute,
        true
    )
    // call your viewmodel setAlarms(alarmInfo)

}


