package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote.Constant.END_POINT_WEATHER_API
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(END_POINT_WEATHER_API)
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherDto
}