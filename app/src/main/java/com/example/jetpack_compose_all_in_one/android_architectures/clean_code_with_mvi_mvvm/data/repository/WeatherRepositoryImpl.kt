package com.abanoub.weather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.abanoub.weather.data.mappers.toWeatherInfo
import com.abanoub.weather.data.remote.WeatherApi
import com.abanoub.weather.domain.repository.WeatherRepository
import com.abanoub.weather.domain.util.Resource
import com.abanoub.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeather(
                    latitude = lat,
                    longitude = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "Unknown Error")
        }
    }
}