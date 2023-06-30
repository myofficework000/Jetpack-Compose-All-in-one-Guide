package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyUnits(
    @Json(name = "pressure_msl")
    val pressureMsl: String,
    @Json(name = "relativehumidity_2m")
    val relativehumidity2m: String,
    @Json(name = "temperature_2m")
    val temperature2m: String,
    @Json(name = "time")
    val time: String,
    @Json(name = "weathercode")
    val weathercode: String,
    @Json(name = "windspeed_10m")
    val windspeed10m: String
)