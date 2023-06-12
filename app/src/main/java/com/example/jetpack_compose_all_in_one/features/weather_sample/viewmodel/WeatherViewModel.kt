package com.example.jetpack_compose_all_in_one.features.weather_sample.viewmodel

import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto.Forecast
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository.RemoteWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel(
    private val repository: RemoteWeatherRepository
) : ViewModel() {
    var weatherData = MutableLiveData<WeatherResponse>()
    var fiveDaysData = MutableLiveData<List<Forecast>>()
    val isFahrenheit = mutableStateOf(false)

    private fun getWeather(city: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = repository.getWeather(city)
            if (response.isSuccessful) {
                weatherData.postValue(response.body())
            }
        } catch (networkError: IOException) {
            networkError.printStackTrace()
        }
    }

    private fun getFiveDaysWeather(city: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val response = repository.getFiveDays3HourWeather(city)
            if (response.isSuccessful) {
                response.body()?.list?.isNotEmpty().let {
                    fiveDaysData.postValue(response.body()?.list)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getWeather(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getWeatherUsingLatLng(location)
                if (response.isSuccessful) {
                    weatherData.postValue(response.body())
                }
            } catch (networkError: IOException) {
                networkError.printStackTrace()
            }
        }
    }

    private fun getFiveDaysWeather(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getFiveDays3HourWeatherUsingLatLng(location)
                if (response.isSuccessful) {
                    response.body()?.list?.isNotEmpty().let {
                        fiveDaysData.postValue(response.body()?.list)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun updateWeatherByLocation(location: Location) {
        getWeather(location)
        getFiveDaysWeather(location)
    }

    fun updateWeatherByCity(city: String) {
        getWeather(city)
        getFiveDaysWeather(city)
    }
}