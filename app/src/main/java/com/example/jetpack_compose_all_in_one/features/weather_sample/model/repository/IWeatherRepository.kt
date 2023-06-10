package com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto.ForecastResponse
import retrofit2.Response

interface IWeatherRepository {
    suspend fun getWeather(city: String): Response<WeatherResponse>

    suspend fun getWeatherUsingLatLng(location: Location): Response<WeatherResponse>

    val weatherResponse: MutableLiveData<WeatherResponse>

    suspend fun getFiveDays3HourWeather(city: String): Response<ForecastResponse>

    suspend fun getFiveDays3HourWeatherUsingLatLng(location: Location): Response<ForecastResponse>

    val fiveDaysWeatherResponse: MutableLiveData<ForecastResponse>
}