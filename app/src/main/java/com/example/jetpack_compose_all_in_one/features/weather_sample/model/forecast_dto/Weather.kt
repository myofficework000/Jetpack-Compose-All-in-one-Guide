package com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)