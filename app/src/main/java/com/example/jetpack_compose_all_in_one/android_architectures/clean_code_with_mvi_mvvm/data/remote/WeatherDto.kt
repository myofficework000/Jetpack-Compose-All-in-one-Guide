package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)