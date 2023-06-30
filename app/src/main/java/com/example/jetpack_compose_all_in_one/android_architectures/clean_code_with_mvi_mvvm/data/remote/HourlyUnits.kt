package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote

data class HourlyUnits(
    val relativehumidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val windspeed_10m: String
)