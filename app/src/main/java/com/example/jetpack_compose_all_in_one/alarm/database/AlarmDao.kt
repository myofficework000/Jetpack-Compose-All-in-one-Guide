package com.example.jetpack_compose_all_in_one.alarm.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmDao {

    @Insert
    fun setAnAlarm(alarmInfo: AlarmInfo)

    @Query("Select * From AlarmInfo")
    fun getAllAlarms():List<AlarmInfo>
}