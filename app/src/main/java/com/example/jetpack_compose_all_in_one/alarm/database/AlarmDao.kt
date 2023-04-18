package com.example.jetpack_compose_all_in_one.alarm.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import androidx.lifecycle.LiveData

@Dao
interface AlarmDao {

    @Insert
    fun setAnAlarm(alarmInfo: AlarmInfo)

    @Query("Select * From AlarmInfo")
    fun getAllAlarms():List<AlarmInfo>
}