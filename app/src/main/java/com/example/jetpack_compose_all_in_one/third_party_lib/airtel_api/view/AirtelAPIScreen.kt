package com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.viewmodel.AirtelViewModel

@Composable
fun AirtelAPIScreen(airtelViewModel: AirtelViewModel) {
    val result by airtelViewModel.result.observeAsState()
    var countryResult by remember { mutableStateOf("") }
    var cityResult by remember { mutableStateOf("") }
    var asnResult by remember { mutableStateOf("") }
    var latitudeResult by remember { mutableFloatStateOf(0f) }
    var longitudeResult by remember { mutableFloatStateOf(0f) }

    result?.let {
        countryResult = it.country
        cityResult = it.city
        asnResult = it.asn
        latitudeResult = it.lat
        longitudeResult = it.lon
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        var ipAddressInput by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Country: $countryResult")
        Text(text = "City: $cityResult")
        Text(text = "Asn: $asnResult")
        Text(text = "Latitude: $latitudeResult")
        Text(text = "Longitude: $longitudeResult")

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = ipAddressInput,
            onValueChange = { newValue ->
                ipAddressInput = newValue
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { airtelViewModel.showLocationDetailsOnIp(ipAddressInput) }
        ){
            Text(text = "Get Details")
        }
    }

}
