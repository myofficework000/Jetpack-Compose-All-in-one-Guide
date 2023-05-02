package com.example.jetpack_compose_all_in_one.features.internet

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InternetViewModel @Inject constructor(): ViewModel() {
    val networkState = mutableStateOf<NetworkState>(NetworkState.Connected)

    // Doesn't support cellular data yet
    val networkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    val networkCallback = object: ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            networkState.value = NetworkState.Connected
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            networkState.value = NetworkState.Disconnected
        }
    }
}