package com.example.jetpack_compose_all_in_one.features.weather_sample.view

import android.app.Application
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.ApiWeatherService
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.RetrofitBuilder
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository.RemoteWeatherRepository
import com.example.jetpack_compose_all_in_one.features.weather_sample.viewmodel.WeatherViewModel

@Composable
fun WeatherSample() {
    //Luan will add UI and data for this
    val weatherViewModel: WeatherViewModel = initViewModel()
    val weatherData = weatherViewModel.weatherData.observeAsState()
    LaunchedEffect(weatherData) {
        weatherViewModel.getWeather(city = "California")
    }

    val temp = weatherData.value?.main?.temp.toString() ?: ""
    Text(text = temp)

}

fun initViewModel(): WeatherViewModel {
    val remoteWeatherRepository = RemoteWeatherRepository(RetrofitBuilder.getRetrofit().create(ApiWeatherService::class.java))
    return WeatherViewModel(remoteWeatherRepository)
}
