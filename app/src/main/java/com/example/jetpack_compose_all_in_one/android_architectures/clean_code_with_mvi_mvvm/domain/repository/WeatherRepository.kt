package com.abanoub.weather.domain.repository

import com.abanoub.weather.domain.util.Resource
import com.abanoub.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}