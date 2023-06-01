package com.example.jetpack_compose_all_in_one.features.weather_sample.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.forecast_dto.Forecast
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.ApiWeatherService
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.RetrofitBuilder
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository.RemoteWeatherRepository
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants.IMG_URL
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Converters
import com.example.jetpack_compose_all_in_one.features.weather_sample.viewmodel.WeatherViewModel
import com.example.jetpack_compose_all_in_one.ui.components.LabeledSwitch
import com.example.jetpack_compose_all_in_one.ui.theme.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WeatherSample() {
    var isButtonClicked by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf(0) }
    var job: Job? by remember { mutableStateOf(null) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        if (!isButtonClicked) {
            androidx.compose.material.TextButton(
                onClick = { isButtonClicked = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Click Me")
            }
        } else {
            Text("Timer: $time seconds")
        }
    }

    LaunchedEffect(isButtonClicked) {
        if (isButtonClicked) {
            job = coroutineScope.launch {
                while (isButtonClicked) {
                    delay(1000)
                    time++
                }
            }
        } else {
            job?.cancel()
            time = 0
        }
    }

    /*
        val weatherViewModel: WeatherViewModel = initViewModel()
        val weatherData = weatherViewModel.weatherData.observeAsState()
        var inputValue by remember { mutableStateOf(TextFieldValue("")) }
        val forecastData = weatherViewModel.fiveDaysData.observeAsState()

        LaunchedEffect(weatherData) {
            weatherViewModel.getWeather(city = "California")
        }

        val city = weatherData.value?.name.toString()
    //    val temp = weatherData.value?.main?.temp?.let{ Converters.tempConverter(it)}.toString()
    //    val feelLikeTemp = weatherData.value?.main?.feels_like?.let{ Converters.tempConverter(it)}.toString()
        val description = weatherData.value?.weather?.get(0)?.description.toString()
    //    val humidity = weatherData.value?.main?.humidity.toString()
    //    val visibility = weatherData.value?.visibility?.div(1000).toString()
    //    val pressure = weatherData.value?.main?.pressure.toString()
    //    val maxTemp = weatherData.value?.main?.temp_max?.let{ Converters.tempConverter(it)}.toString()
    //    val minTemp = weatherData.value?.main?.temp_min?.let{ Converters.tempConverter(it)}.toString()
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
                .fillMaxWidth()
                .fillMaxHeight()
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

            forecastData.value?.let { FiveDaysForecast(it, viewModel = weatherViewModel) }
        }*/
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherCard(
    city: String,
    temp: String,
    feelLikeTemp: String,
    description: String,
    humidity: String,
    visibility: String,
    pressure: String,
    maxTemp: String,
    minTemp: String,
    weatherIcon: String?,
    date: String,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    cardPadding: PaddingValues = PaddingValues(dp_20)
) {
    Card(
        modifier = modifier.then(Modifier.padding(dp_15)),
        shape = RoundedCornerShape(dp_15),
        elevation = CardDefaults.cardElevation(dp_15),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = date,
                fontSize = 15.sp,
                color = Color.Red
            )

            Text(
                text = city,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                GlideImage(
                    model = "${IMG_URL}/${weatherIcon}.png",
                    contentDescription = "",
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
                Text(
                    text = " ${temp}℉",
                    fontSize = 30.sp
                )
            }
            Text(
                text = "Feels like ${feelLikeTemp}℉, $description",
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
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(dp_1)
                )
                Column {
                    Text(
                        text = "${maxTemp}℉ / ${minTemp}℉",
                        fontSize = 17.sp,
                    )
                    Text(
                        text = "Humidity: ${humidity}%",
                        fontSize = 17.sp,
                    )
                    Text(
                        text = "Visibility: ${visibility}km",
                        fontSize = 17.sp,
                    )
                    Text(
                        text = "Pressure: ${pressure}hPa",
                        fontSize = 17.sp,
                    )
                }
            }
        }
    }
}

// Feel free to deprecate the one above when confirmed
// Note that the input value is Celsius. It'll change
//      to Fahrenheit here.
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
                GlideImage(
                    model = "${IMG_URL}/${weatherIcon}.png",
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
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

private fun Double.toReadable(isFahrenheit: Boolean) =
    if (isFahrenheit) "%.1f℉".format(this * 1.8 + 32) else "$this℃"


fun initViewModel(): WeatherViewModel {
    val remoteWeatherRepository =
        RemoteWeatherRepository(RetrofitBuilder.getRetrofit().create(ApiWeatherService::class.java))
    return WeatherViewModel(remoteWeatherRepository)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAllInOneTheme {
        WeatherSample()
    }
}