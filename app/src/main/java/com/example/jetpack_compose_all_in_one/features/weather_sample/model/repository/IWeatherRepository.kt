package com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import retrofit2.Response

interface IWeatherRepository {
    suspend fun getWeather(city: String): Response<WeatherResponse>

    val weatherResponse: MutableLiveData<WeatherResponse>
}