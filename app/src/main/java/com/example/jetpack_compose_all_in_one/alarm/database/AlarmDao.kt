package com.example.jetpack_compose_all_in_one.alarm.database

import androidx.room.*

@Dao
interface AlarmDao {

    @Insert
    fun setAnAlarm(alarmInfo: AlarmInfo): Long

    @Update
    fun updateAlarm(alarmInfo: AlarmInfo)

    @Query("Select * From AlarmInfo")
    fun getAllAlarms():List<AlarmInfo>

    @Delete
    fun removeAlarm(alarmInfo: AlarmInfo)
}