package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.repository

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.mappers.toWeatherInfo
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.repository.WeatherRepository
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.util.Resource
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherInfo
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

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