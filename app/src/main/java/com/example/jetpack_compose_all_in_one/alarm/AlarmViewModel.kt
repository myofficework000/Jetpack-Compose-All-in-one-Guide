package com.example.jetpack_compose_all_in_one.alarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.alarm.database.AlarmDao
import com.example.jetpack_compose_all_in_one.alarm.database.AlarmInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmDao: AlarmDao
) : ViewModel() {
    var alarmTime: MutableLiveData<List<AlarmInfo>> = MutableLiveData()

    init { getAllAlarms() }

    fun setAlarms(alarmInfo: AlarmInfo): Int {
        //call dao to set info
        val alarmId = alarmDao.setAnAlarm(alarmInfo).toInt()
        println("added $alarmId at ${alarmInfo.hour}:${alarmInfo.minute}")
        getAllAlarms() // For refreshing the list
        return alarmId
    }

    fun toggleAlarm(alarmInfo: AlarmInfo, enabled: Boolean) {
        alarmDao.updateAlarm( alarmInfo.copy(enabled = enabled) )
    }

    fun removeAlarm(alarmInfo: AlarmInfo) {
        alarmDao.removeAlarm(alarmInfo)
        getAllAlarms()
        println("deleted ${alarmInfo.id} at ${alarmInfo.hour}:${alarmInfo.minute}")
    }

    private fun getAllAlarms() {
        val listOfAlarmInfo = alarmDao.getAllAlarms()
        alarmTime.value = listOfAlarmInfo
        println("Alarm count: ${listOfAlarmInfo.count()}")
    }
}