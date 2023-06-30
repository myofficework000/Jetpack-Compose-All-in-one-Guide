package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote

data class Hourly(
    val relativehumidity_2m: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val windspeed_10m: List<Double>
)