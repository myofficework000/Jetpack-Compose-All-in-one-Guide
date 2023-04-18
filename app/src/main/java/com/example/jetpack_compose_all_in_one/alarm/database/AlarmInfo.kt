package com.example.jetpack_compose_all_in_one.alarm.database

import android.arch.persistence.room.Entity


@Entity(tableName = "AlarmInfo")
data class AlarmInfo(
    val reqCode: Int,
    val hour: Int,
    val minute: Int,
    val enabled: Boolean
)