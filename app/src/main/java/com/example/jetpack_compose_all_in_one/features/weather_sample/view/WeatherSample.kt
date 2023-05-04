package com.example.jetpack_compose_all_in_one.features.weather_sample.view


import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.data.WeatherResponse
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.ApiWeatherService
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote.RetrofitBuilder
import com.example.jetpack_compose_all_in_one.features.weather_sample.model.repository.RemoteWeatherRepository
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants.IMG_URL
import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Converters
import com.example.jetpack_compose_all_in_one.features.weather_sample.viewmodel.WeatherViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WeatherSample() {
    //Luan will add UI and data for this
    val weatherViewModel: WeatherViewModel = initViewModel()
    val weatherData = weatherViewModel.weatherData.observeAsState()
    var inputValue by remember{ mutableStateOf(TextFieldValue(""))}
    val scope = rememberCoroutineScope()

     LaunchedEffect(weatherData) {
        weatherViewModel.getWeather(city = "California")
    }

    val city = weatherData.value?.name.toString()
    val temp = weatherData.value?.main?.temp?.let{ Converters.tempConverter(it)}.toString()
    val feelLikeTemp = weatherData.value?.main?.feels_like?.let{ Converters.tempConverter(it)}.toString()
    val description = weatherData.value?.weather?.get(0)?.description.toString()
    val humidity = weatherData.value?.main?.humidity.toString()
    val visibility = weatherData.value?.visibility?.div(1000).toString()
    val pressure = weatherData.value?.main?.pressure.toString()
    val maxTemp = weatherData.value?.main?.temp_max?.let{ Converters.tempConverter(it)}.toString()
    val minTemp = weatherData.value?.main?.temp_min?.let{ Converters.tempConverter(it)}.toString()
    val weatherIcon = weatherData.value?.weather?.get(0)?.icon.toString()
    val date = weatherData.value?.dt?.let { Converters.dateConverter(it) }.toString()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(dp_10),
        verticalArrangement = Arrangement.spacedBy(dp_4),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextField(
                modifier = Modifier.fillMaxHeight(),
                value = inputValue,
                onValueChange = {newValue ->
                    inputValue = newValue
                },
                placeholder = {Text(text = "Search city")}
            )
            Button(
                modifier = Modifier.fillMaxHeight(),
                shape = RectangleShape,
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        weatherViewModel.getWeather(city = inputValue.text)
                    }
                }
            ) {
                Text(text = "Search")
            }
        }
        WeatherCard(
            city,
            temp,
            feelLikeTemp,
            description,
            humidity,
            visibility,
            pressure,
            maxTemp,
            minTemp,
            weatherIcon,
            date,
        )
    }
}
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
            Row (
                horizontalArrangement = Arrangement.Start
            ){
                GlideImage(
                    imageModel = "${IMG_URL}/${weatherIcon}.png",
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
                Text(
                    text = " ${temp}℉",
                    fontSize = 30.sp)
            }
            Text(
                text = "Feels like ${feelLikeTemp}℉, ${description}",
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
                Column() {
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