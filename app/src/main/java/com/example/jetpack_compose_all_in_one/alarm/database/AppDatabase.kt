package com.example.jetpack_compose_all_in_one.alarm.database

import android.arch.persistence.room.Database

@Database(entities = [AlarmInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase {

    abstract fun getAlarmDao(): AlarmDao
}