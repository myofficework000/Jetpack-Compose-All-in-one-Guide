package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.theme.Blue10
import com.example.jetpack_compose_all_in_one.ui.theme.L1BoxColor2
import com.example.jetpack_compose_all_in_one.utils.requestAllLocation
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    var isLocationAvailable by remember { mutableStateOf(false) }
    val requestingLocation = requestAllLocation { isLocationAvailable = it }

    LaunchedEffect(Unit) {
        requestingLocation.launchMultiplePermissionRequest()
    }

    LaunchedEffect(isLocationAvailable) {
        if (isLocationAvailable) {
            viewModel.loadWeatherInfo()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue10)
        ) {
            WeatherCard(
                state = viewModel.state,
                backgroundColor = L1BoxColor2
            )
            Spacer(modifier = Modifier.height(16.dp))
            WeatherForecast(state = viewModel.state)
        }
        if (viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}