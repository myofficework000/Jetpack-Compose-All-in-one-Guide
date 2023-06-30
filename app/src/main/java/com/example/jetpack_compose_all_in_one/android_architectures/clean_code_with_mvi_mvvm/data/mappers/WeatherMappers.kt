package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.mappers

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote.WeatherResponse
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherData
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherInfo
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherResponse.toWeatherDataMap(): Map<Int, List<WeatherData>> = hourly.run {
    time.mapIndexed { index, time ->
        val temperature = temperature2m[index]
        val weatherCode = weathercode[index]
        val windSpeed = windspeed10m[index]
        val pressure = pressureMsl[index]
        val humidity = relativehumidity2m[index]

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity.toDouble(),
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { x -> x.data }
    }
}

fun WeatherResponse.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = this.toWeatherDataMap()
    val now = LocalDateTime.now()

    // This is basically rounding the current time to nearest hour
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}