package com.example.jetpack_compose_all_in_one.features.alarm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpack_compose_all_in_one.features.profile_update.Converter
import com.example.jetpack_compose_all_in_one.features.profile_update.Profile
import com.example.jetpack_compose_all_in_one.features.profile_update.ProfileDao

@Database(
    entities = [AlarmInfo::class, Profile::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAlarmDao(): AlarmDao
    abstract fun getProfileDao(): ProfileDao
}