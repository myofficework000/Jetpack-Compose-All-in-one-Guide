package com.example.jetpack_compose_all_in_one.features.weather_sample.utils

import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants.TEMP_CONVERT2
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants.TEMP_CONVERTER1
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object Converters {
    fun tempConverter(temp: Double): String{
        return (temp * TEMP_CONVERTER1 + TEMP_CONVERT2).roundToInt().toString()
    }

    fun dateConverter(dt: Int): String{
        val date = Date(dt * 1000L)
        return SimpleDateFormat("E, MMM dd h:mm a").format(date)
    }
}