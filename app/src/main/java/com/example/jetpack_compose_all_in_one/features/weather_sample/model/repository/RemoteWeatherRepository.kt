package com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.ApiWeatherService
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants.API_KEY
import retrofit2.Response

class RemoteWeatherRepository(
    private val weatherService: ApiWeatherService
) : IWeatherRepository {
    override val weatherResponse = MutableLiveData<WeatherResponse>()

    override suspend fun getWeather(city: String): Response<WeatherResponse> {
        return weatherService.getWeather(city, API_KEY)
    }
}