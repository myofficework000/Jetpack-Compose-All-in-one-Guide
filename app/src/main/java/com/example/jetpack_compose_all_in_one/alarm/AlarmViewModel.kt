package com.example.jetpack_compose_all_in_one.alarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor() : ViewModel() {
    var alarmTime: MutableLiveData<AlarmInfo> = MutableLiveData()

    fun setAlarms(alarmInfo: AlarmInfo) {
        //call dao to set info
    }

    fun getAllAlarms(listOfAlarmInfo: List<AlarmInfo>) {
        alarmTime.value = listOfAlarmInfo[0]
    }
}