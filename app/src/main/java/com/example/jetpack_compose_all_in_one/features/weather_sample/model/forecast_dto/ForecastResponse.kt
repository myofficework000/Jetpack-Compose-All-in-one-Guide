package com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)