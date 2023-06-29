package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.mappers

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherData
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherInfo
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.weather.WeatherType
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote.WeatherDataDto
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.remote.WeatherDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}