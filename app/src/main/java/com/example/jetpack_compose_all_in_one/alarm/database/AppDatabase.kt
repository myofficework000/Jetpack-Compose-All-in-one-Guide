package com.example.jetpack_compose_all_in_one.alarm.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlarmInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getAlarmDao(): AlarmDao
}