package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "pressure_msl")
    val pressureMsl: List<Double>,
    @Json(name = "relativehumidity_2m")
    val relativehumidity2m: List<Int>,
    @Json(name = "temperature_2m")
    val temperature2m: List<Double>,
    @Json(name = "time")
    val time: List<String>,
    @Json(name = "weathercode")
    val weathercode: List<Int>,
    @Json(name = "windspeed_10m")
    val windspeed10m: List<Double>
)