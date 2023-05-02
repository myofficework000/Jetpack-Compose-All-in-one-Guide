package com.example.jetpack_compose_all_in_one.features.internet

sealed class NetworkState {
    object Connected: NetworkState()
    object Disconnected: NetworkState()
}
