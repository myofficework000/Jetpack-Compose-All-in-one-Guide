package com.example.jetpack_compose_all_in_one.features.play_with_maps

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(

): ViewModel() {
    val cameraPositionState = CameraPositionState(
        CameraPosition.fromLatLngZoom(Constants.MAP_POS_BIGBEN, 18.54f)
    )

    val mapType = mutableStateOf(GoogleMap.MAP_TYPE_NORMAL)
    val mapTypeDisplayName by derivedStateOf {
        when (mapType.value) {
            GoogleMap.MAP_TYPE_NORMAL -> "Normal"
            GoogleMap.MAP_TYPE_SATELLITE -> "Satellite"
            GoogleMap.MAP_TYPE_TERRAIN -> "Terrain"
            GoogleMap.MAP_TYPE_HYBRID -> "Hybrid"
            else -> "None"
        }
    }

    fun changeMapType() {
        mapType.value = when (mapType.value) {
            GoogleMap.MAP_TYPE_NONE -> GoogleMap.MAP_TYPE_NORMAL
            GoogleMap.MAP_TYPE_NORMAL -> GoogleMap.MAP_TYPE_SATELLITE
            GoogleMap.MAP_TYPE_SATELLITE -> GoogleMap.MAP_TYPE_TERRAIN
            GoogleMap.MAP_TYPE_TERRAIN -> GoogleMap.MAP_TYPE_HYBRID
            else -> GoogleMap.MAP_TYPE_NONE
        }
    }
}