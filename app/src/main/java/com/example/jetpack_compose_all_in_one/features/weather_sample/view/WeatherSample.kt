package com.example.jetpack_compose_all_in_one.features.weather_sample.view


import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.*
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherSample(getCurrentLocationFunc: ((Location?) -> Unit) -> Unit) {
    val weatherViewModel: WeatherViewModel = initViewModel()
    val forecastData = weatherViewModel.fiveDaysData.observeAsState()
    val weatherData = weatherViewModel.weatherData.observeAsState()
    var isLocationAvailable by remember { mutableStateOf(false) }
    val requestingLocation = requestAllLocation { isLocationAvailable = it }
 /*   val isReadyToSetUI: Boolean by remember {
        derivedStateOf {
            (((forecastData.value?.isNotEmpty() == true) &&
                    (weatherData.value?.weather?.isNotEmpty() == true)
                    )
                    )
        }
    }*/

    LaunchedEffect(isLocationAvailable) {
        requestingLocation.launchMultiplePermissionRequest()
        getCurrentLocationFunc {
            it?.let { location ->
                weatherViewModel.getWeather(location = location)
                weatherViewModel.getFiveDaysWeather(location = location)
            }
        }
    }

    Log.i("tag",forecastData.value?.isNotEmpty().toString())
    Log.i("tag",forecastData.value?.get(0)?.dt.toString())
    Log.i("tag",weatherData.value?.weather?.isNotEmpty().toString())

    if (weatherData.value?.weather?.isNotEmpty() == true
    ) {
        InflateWeatherUI(weatherData, forecastData, weatherViewModel)
    }
}

@Composable
private fun InflateForeCastUI(
    forecastData: State<List<Forecast>?>,
    weatherViewModel: WeatherViewModel
) {
    forecastData.value?.let { FiveDaysForecast(it, viewModel = weatherViewModel) }
}

@Composable
private fun InflateWeatherUI(
    weatherData: State<WeatherResponse?>,
    forecastData: State<List<Forecast>?>,
    weatherViewModel: WeatherViewModel,
) = with(weatherData) {
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }
    val city = weatherData.value?.name.toString()
    val description = weatherData.value?.weather?.get(0)?.description.toString()
    val weatherIcon = weatherData.value?.weather?.get(0)?.icon.toString()
    val date = weatherData.value?.dt?.let { Converters.dateConverter(it) }.toString()
    val temp = weatherData.value?.main?.temp
    val feelLikeTemp = weatherData.value?.main?.feels_like
    val maxTemp = weatherData.value?.main?.temp_max
    val minTemp = weatherData.value?.main?.temp_min
    val humidity = weatherData.value?.main?.humidity
    val visibility = weatherData.value?.visibility
    val pressure = weatherData.value?.main?.pressure

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(dp_10),
        verticalArrangement = Arrangement.spacedBy(dp_4),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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

                SimpleIconButton(iconResourceInt = R.drawable.baseline_search_24) {
                    with(weatherViewModel) {
                        getWeather(city = inputValue.text)
                        getFiveDaysWeather(city = inputValue.text)
                    }
                }
            }
        }
        WeatherCard(
            city = city,
            description = description,
            weatherIcon = weatherIcon,
            date = date,
            temp = temp,
            feelLikeTemp = feelLikeTemp,
            maxTemp = maxTemp,
            minTemp = minTemp,
            humidity = humidity?.toDouble(),
            visibility = visibility?.toDouble(),
            pressure = pressure?.toDouble(),
            isFahrenheit = weatherViewModel.isFahrenheit
        )

        InflateForeCastUI(forecastData, weatherViewModel)
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
                    .padding(cardPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
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


@Composable
fun FiveDaysForecast(list: List<Forecast>, viewModel: WeatherViewModel) {
    LazyRow(
        modifier = Modifier.wrapContentHeight()
    ) {
        items(list) { item ->
            Forecast(
                description = item.weather[0].description,
                weatherIcon = item.weather[0].icon,
                date = Converters.dayConverter(item.dt),
                maxTemp = item.main.temp_max,
                minTemp = item.main.temp_min,
                isFahrenheit = viewModel.isFahrenheit
            )
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
        modifier = modifier.then(
            Modifier
                .padding(dp_15)
        ),
        shape = RoundedCornerShape(dp_15),
        elevation = CardDefaults.cardElevation(dp_15),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardPadding)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = date)

            Row(horizontalArrangement = Arrangement.Start) {
                weatherIcon?.let {
                    GlideImage(
                        model = "${IMG_URL}/${weatherIcon}.png",
                        contentDescription = "",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }

            Text(text = description)
            Text(
                "${maxTemp?.toReadable(isFahrenheit.value)}", fontSize = 17.sp
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "${minTemp?.toReadable(isFahrenheit.value)}", fontSize = 17.sp
            )
        }
    }
}

fun initViewModel(): WeatherViewModel {
    val remoteWeatherRepository =
        RemoteWeatherRepository(RetrofitBuilder.getRetrofit().create(ApiWeatherService::class.java))
    return WeatherViewModel(remoteWeatherRepository)
}