package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.repository

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.util.Resource
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}