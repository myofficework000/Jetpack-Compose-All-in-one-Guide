package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote

object Constant {
    const val END_POINT_WEATHER_API =
        "v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"
    const val BASE_URL_WEATHER_API = "https://api.open-meteo.com/"
}