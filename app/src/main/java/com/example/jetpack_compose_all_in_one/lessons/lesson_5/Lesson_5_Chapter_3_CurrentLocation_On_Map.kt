package com.example.jetpack_compose_all_in_one.lessons.lesson_5

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.PAGER_BACKGROUND
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.requestAllLocation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay

@Composable
fun Lesson_5_Chapter_3_CurrentLocation_On_Map(
    getCurrentLocationFunc: ((Location?) -> Unit) -> Unit
) {
    LessonContent(getCurrentLocationFunc)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun LessonContent(
    getCurrentLocationFunc: ((Location?) -> Unit) -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            Constants.MAP_POS_BIGBEN,
            Constants.MAP_DEFAULT_ZOOM_1
        )
    }

    var isLocationAvailable by remember{ mutableStateOf(false) }
    val requestingLocation = requestAllLocation{ isLocationAvailable = it }
    var currentLocation by remember { mutableStateOf<Location?>(null) }
    val currentLocationMarker = remember{ derivedStateOf { MarkerState(
        if (currentLocation == null) Constants.MAP_POS_BIGBEN
        else LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
    ) } }

    LaunchedEffect(Unit) {
        requestingLocation.launchMultiplePermissionRequest()
    }

    LaunchedEffect(isLocationAvailable) {
        while (isLocationAvailable) {
            getCurrentLocationFunc{
                currentLocation = it

                it?.let { loc ->
                    cameraPositionState.move(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition(
                                LatLng(loc.latitude, loc.longitude),
                                cameraPositionState.position.zoom,
                                cameraPositionState.position.tilt,
                                cameraPositionState.position.bearing
                            )
                        )
                    )
                }
            }
            delay(Constants.LOCATION_UPDATE_INTERVAL)
        }
    }

    Column( Modifier.fillMaxSize().background(PAGER_BACKGROUND) ) {
        LessonHeader(
            stringResource(id = R.string.l5c3_header_text),
            Modifier
                .fillMaxWidth()
                .padding(dp_15),
            TextAlign.Center
        )

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp_15),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = currentLocationMarker.value,
                title = "This is you.",
                snippet = "..."
            )
        }
    }
}