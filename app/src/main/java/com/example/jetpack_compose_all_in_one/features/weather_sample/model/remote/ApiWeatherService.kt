package com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote

import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto.ForecastResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeatherService {

    @GET(Constants.END_POINT_WEATHER)
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse>

    @GET(Constants.END_POINT_WEATHER)
    suspend fun getWeatherUsingLatLng(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse>


    @GET(Constants.END_POINT_5DAY_3HOUR)
    suspend fun get5Days3HoursWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<ForecastResponse>

    @GET(Constants.END_POINT_5DAY_3HOUR)
    suspend fun get5Days3HoursWeatherUsingLatLng(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<ForecastResponse>
}