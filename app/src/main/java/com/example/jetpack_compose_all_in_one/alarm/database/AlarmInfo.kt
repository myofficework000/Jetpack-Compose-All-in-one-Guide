package com.example.jetpack_compose_all_in_one.alarm.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AlarmInfo")
data class AlarmInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val hour: Int,
    val minute: Int,
    val enabled: Boolean
)