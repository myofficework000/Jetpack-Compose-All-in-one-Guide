package com.example.jetpack_compose_all_in_one.features.weather_sample.view

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.ApiWeatherService
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.RetrofitBuilder
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository.RemoteWeatherRepository
import com.example.jetpack_compose_all_in_one.features.weather_sample.viewmodel.WeatherViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.*

@Composable
fun WeatherSample() {
    //Luan will add UI and data for this
    val weatherViewModel: WeatherViewModel = initViewModel()
    val weatherData = weatherViewModel.weatherData.observeAsState()
    LaunchedEffect(weatherData) {
        weatherViewModel.getWeather(city = "California")
    }

    val temp = weatherData.value?.main?.temp.toString()
    val city = weatherData.value?.name.toString()
    val maxTemp = weatherData.value?.main?.temp_max.toString()
    val minTemp = weatherData.value?.main?.temp_min.toString()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(dp_10),
        verticalArrangement = Arrangement.spacedBy(dp_4),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = city)
        Text(text = temp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = minTemp)
            Text(text = "/")
            Text(text = maxTemp)
        }
    }
}

fun initViewModel(): WeatherViewModel {
    val remoteWeatherRepository = RemoteWeatherRepository(RetrofitBuilder.getRetrofit().create(ApiWeatherService::class.java))
    return WeatherViewModel(remoteWeatherRepository)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAllInOneTheme {
        WeatherSample()
    }
}