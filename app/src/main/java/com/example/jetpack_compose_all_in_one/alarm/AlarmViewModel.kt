package com.example.jetpack_compose_all_in_one.alarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.alarm.database.AlarmDao
import com.example.jetpack_compose_all_in_one.alarm.database.AlarmInfo
import com.example.jetpack_compose_all_in_one.alarm.database.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val alarmDao: AlarmDao
) : ViewModel() {
    var alarmTime: MutableLiveData<List<AlarmInfo>> = MutableLiveData()

    fun setAlarms(alarmInfo: AlarmInfo) {
        //call dao to set info
        alarmDao.setAnAlarm(alarmInfo)
    }

    fun getAllAlarms() {
        val listOfAlarmInfo = alarmDao.getAllAlarms()
        alarmTime.value = listOfAlarmInfo
    }
}