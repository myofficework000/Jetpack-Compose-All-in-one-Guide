package com.example.jetpack_compose_all_in_one.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import com.example.jetpack_compose_all_in_one.utils.Constants.ALARM_REQUEST_CODE

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
}