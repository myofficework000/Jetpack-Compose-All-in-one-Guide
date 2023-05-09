package com.example.jetpack_compose_all_in_one.features.play_with_maps

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(

): ViewModel() {
    val cameraPositionState = CameraPositionState(
        CameraPosition.fromLatLngZoom(Constants.MAP_POS_BIGBEN, 18.54f)
    )

    val mapType2 = mutableStateOf(MapType.NORMAL)
    val mapTypeDisplayName by derivedStateOf {
        when (mapType2.value) {
            MapType.NORMAL -> "Normal"
            MapType.SATELLITE -> "Satellite"
            MapType.TERRAIN -> "Terrain"
            MapType.HYBRID -> "Hybrid"
            else -> "None"
        }
    }

    fun changeMapType() {
        mapType2.value = when (mapType2.value) {
            MapType.NONE -> MapType.NORMAL
            MapType.NORMAL -> MapType.SATELLITE
            MapType.SATELLITE -> MapType.TERRAIN
            MapType.TERRAIN -> MapType.HYBRID
            else -> MapType.NONE
        }
    }
}