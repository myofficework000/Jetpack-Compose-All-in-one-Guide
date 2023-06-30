package com.example.jetpack_compose_all_in_one.features.weather_sample.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.weather_sample.viewmodel.WeatherViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.dp_20
import com.example.jetpack_compose_all_in_one.ui.theme.sp_25

@Composable
fun WeatherDetails(viewModel: WeatherViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = CenterHorizontally
    ) {
        viewModel.weatherData.value?.let { Text(text = it.name, fontSize = sp_25, modifier = Modifier.padding(top = dp_20)) }
        Text(text = String.format("Temp: ${viewModel.weatherData.value?.main?.temp.toString()} ℃"))
        Text(text = String.format("Feels like: ${viewModel.weatherData.value?.main?.feels_like.toString()} ℃"))
        WeatherIcon(iconId = viewModel.weatherData.value?.id.toString())
        Text(text = String.format("Humidity: ${viewModel.weatherData.value?.main?.humidity.toString()} %%"))
        Text(
            text = String.format(
                "Visibility: ${
                    (viewModel.weatherData.value?.visibility?.div(1000))?.toDouble().toString()
                } km"
            )
        )
        Text(
            text = String.format(
                "Pressure: ${
                    viewModel.weatherData.value?.main?.pressure?.toDouble().toString()
                } hPa"
            )
        )
    }
}

@Composable
fun WeatherIcon(iconId: String) {
    val iconResId = when (iconId) {
        "01d" -> R.drawable.ic_sunny
        "01n" -> R.drawable.ic_sunny
        "02d" -> R.drawable.ic_cloudy
        "02n" -> R.drawable.ic_cloudy
        "03d", "03n" -> R.drawable.ic_very_cloudy
        "04d", "04n" -> R.drawable.ic_very_cloudy
        "09d", "09n" -> R.drawable.ic_rainshower
        "10d", "10n" -> R.drawable.ic_rainy
        "11d", "11n" -> R.drawable.ic_thunder
        "13d", "13n" -> R.drawable.ic_snowy
        "50d", "50n" -> R.drawable.ic_very_cloudy
        else -> R.drawable.ic_very_cloudy
    }

    Image(
        painter = painterResource(id = iconResId),
        contentDescription = null,
        modifier = Modifier.size(150.dp)
    )

}