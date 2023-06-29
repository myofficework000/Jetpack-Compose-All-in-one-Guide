package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.presentation


data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
