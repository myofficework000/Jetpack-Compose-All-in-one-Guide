package com.example.jetpack_compose_all_in_one.features.weather_sample.view


import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto.Forecast
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.ApiWeatherService
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.RetrofitBuilder
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository.RemoteWeatherRepository
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants.IMG_URL
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Converters
import com.example.jetpack_compose_all_in_one.features.weather_sample.viewmodel.WeatherViewModel
import com.example.jetpack_compose_all_in_one.ui.components.GradientTextField
import com.example.jetpack_compose_all_in_one.ui.components.LabeledSwitch
import com.example.jetpack_compose_all_in_one.ui.components.SimpleIconButton
import com.example.jetpack_compose_all_in_one.ui.theme.*
import com.example.jetpack_compose_all_in_one.utils.requestAllLocation
import com.example.jetpack_compose_all_in_one.utils.toReadable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlin.reflect.KFunction1

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherSample(
    weatherViewModel: WeatherViewModel,
    getCurrentLocationFunc: ((Location?) -> Unit) -> Unit
) {
    val forecastData by weatherViewModel.fiveDaysData.observeAsState()
    val weatherData by weatherViewModel.weatherData.observeAsState()
    var isLocationAvailable by remember { mutableStateOf(false) }
    val requestingLocation = requestAllLocation { isLocationAvailable = it }

    LaunchedEffect(Unit) {
        requestingLocation.launchMultiplePermissionRequest()
    }

    LaunchedEffect(isLocationAvailable) {
        if (isLocationAvailable) {
            getCurrentLocationFunc {
                it?.let { location ->
                    weatherViewModel.updateWeatherByLocation(location)
                }
            }
        }
    }

    InflateWeatherUI(
        weatherData,
        forecastData,
        weatherViewModel.isFahrenheit,
        weatherViewModel::updateWeatherByCity
    )
}

@Composable
private fun InflateWeatherUI(
    weatherData: WeatherResponse?,
    forecastData: List<Forecast>?,
    isFahrenheit: MutableState<Boolean>,
    updateWeatherByCityFunc: KFunction1<String, Unit>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dp_10),
        verticalArrangement = Arrangement.spacedBy(dp_4),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InflateSearchBarUI(updateWeatherByCityFunc)
        weatherData?.let { InflateCurrentWeatherUI(it, isFahrenheit) }
        forecastData?.let { InflateForeCastUI(it, isFahrenheit) }
    }
}

@Composable
private fun InflateSearchBarUI(
    updateWeatherByCityFunc: KFunction1<String, Unit>
) {
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            Modifier.padding(dp_10),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GradientTextField(
                value = inputValue.text,
                gradient = Pink10ToPink80,
                modifier = Modifier.weight(1f)
            ) { inputValue = TextFieldValue(it) }

            SimpleIconButton(R.drawable.baseline_search_24) {
                updateWeatherByCityFunc(inputValue.text)
            }
        }
    }
}

@Composable
private fun InflateCurrentWeatherUI(
    weatherData: WeatherResponse,
    isFahrenheit: MutableState<Boolean>,
    modifier: Modifier = Modifier
) = with(weatherData) {
    val city = name
    val description = weather.firstOrNull()?.description.toString()
    val weatherIcon = weather.firstOrNull()?.icon.toString()
    val date = dt.let { Converters.dateConverter(it) }.toString()
    val temp = main.temp
    val feelLikeTemp = main.feels_like
    val maxTemp = main.temp_max
    val minTemp = main.temp_min
    val humidity = main.humidity
    val visibility = visibility
    val pressure = main.pressure

    WeatherCard(
        modifier = modifier,
        city = city,
        description = description,
        weatherIcon = weatherIcon,
        date = date,
        temp = temp,
        feelLikeTemp = feelLikeTemp,
        maxTemp = maxTemp,
        minTemp = minTemp,
        humidity = humidity.toDouble(),
        visibility = visibility.toDouble(),
        pressure = pressure.toDouble(),
        isFahrenheit = isFahrenheit
    )
}

@Composable
private fun InflateForeCastUI(
    forecastData: List<Forecast>,
    isFahrenheit: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dp_4)
    ) {
        items(forecastData) { item ->
            Forecast(
                description = item.weather[0].description,
                weatherIcon = item.weather[0].icon,
                date = Converters.dayConverter(item.dt),
                maxTemp = item.main.temp_max,
                minTemp = item.main.temp_min,
                isFahrenheit = isFahrenheit
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    city: String,
    description: String,
    weatherIcon: String?,
    date: String,
    temp: Double?,
    feelLikeTemp: Double?,
    maxTemp: Double?,
    minTemp: Double?,
    humidity: Double?,
    visibility: Double?,
    pressure: Double?,
    isFahrenheit: MutableState<Boolean>,
    cardPadding: PaddingValues = PaddingValues(dp_20)
) {
    Card(
        modifier = modifier.then(Modifier.padding(dp_15)),
        shape = RoundedCornerShape(dp_15),
        elevation = CardDefaults.cardElevation(dp_15),
    ) {
        Box(Modifier.fillMaxWidth()) {
            LabeledSwitch(
                Pair("℃", "℉"),
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(cardPadding)
            ) { isFahrenheit.value = it }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(cardPadding)
            ) {
                Text(text = date, fontSize = 15.sp, color = Color.Red, fontWeight = FontWeight.Bold)
                Text(text = city, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Row(horizontalArrangement = Arrangement.Start) {
                    GlideImage(
                        model = "${IMG_URL}/${weatherIcon}.png",
                        contentDescription = "",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = " ${temp?.toReadable(isFahrenheit.value)}", fontSize = 30.sp)
                }
                Text(
                    text = "Feels like ${feelLikeTemp?.toReadable(isFahrenheit.value)}, $description",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(dp_5))

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(IntrinsicSize.Max),
                    horizontalArrangement = Arrangement.spacedBy(dp_15)
                ) {
                    Divider(
                        color = Color.Black, modifier = Modifier
                            .fillMaxHeight()
                            .width(dp_1)
                    )

                    Column {
                        Text(
                            "${maxTemp?.toReadable(isFahrenheit.value)} / ${
                                minTemp?.toReadable(
                                    isFahrenheit.value
                                )
                            }", fontSize = 17.sp
                        )
                        Text("Humidity: ${humidity}%", fontSize = 17.sp)
                        Text("Visibility: ${visibility?.div(1000)}km", fontSize = 17.sp)
                        Text("Pressure: ${pressure}hPa", fontSize = 17.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Forecast(
    modifier: Modifier = Modifier,
    description: String,
    weatherIcon: String?,
    date: String,
    maxTemp: Double?,
    minTemp: Double?,
    isFahrenheit: MutableState<Boolean>,
    cardPadding: PaddingValues = PaddingValues(dp_20)
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(dp_15),
        elevation = CardDefaults.cardElevation(dp_15),
    ) {
        Column(
            modifier = Modifier.padding(cardPadding)
        ) {
            Text(text = date)

            weatherIcon?.let {
                GlideImage(
                    model = "${IMG_URL}/${weatherIcon}.png",
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            }

            Text(text = description)

            Text(
                "Min: ${minTemp?.toReadable(isFahrenheit.value)}",
                fontSize = 17.sp
            )
            Text(
                "Max: ${maxTemp?.toReadable(isFahrenheit.value)}",
                fontSize = 17.sp
            )
        }
    }
}

fun initViewModel(): WeatherViewModel {
    val remoteWeatherRepository =
        RemoteWeatherRepository(RetrofitBuilder.getRetrofit().create(ApiWeatherService::class.java))
    return WeatherViewModel(remoteWeatherRepository)
}