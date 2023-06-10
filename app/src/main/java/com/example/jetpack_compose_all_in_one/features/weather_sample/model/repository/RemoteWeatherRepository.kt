package com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto.ForecastResponse
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

    override suspend fun getFiveDays3HourWeather(city: String): Response<ForecastResponse> {
        return weatherService.get5Days3HoursWeather(city, API_KEY)
    }

    override val fiveDaysWeatherResponse = MutableLiveData<ForecastResponse>()

    override suspend fun getFiveDays3HourWeatherUsingLatLng(location: Location): Response<ForecastResponse> {
        return weatherService.get5Days3HoursWeatherUsingLatLng(
            location.latitude.toString(),
            location.longitude.toString(),
            API_KEY
        )
    }

    override suspend fun getWeatherUsingLatLng(location: Location): Response<WeatherResponse> {
        return weatherService.getWeatherUsingLatLng(
            location.latitude.toString(),
            location.longitude.toString(),
            API_KEY
        )
    }
}