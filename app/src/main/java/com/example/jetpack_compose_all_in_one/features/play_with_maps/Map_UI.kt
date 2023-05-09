package com.example.jetpack_compose_all_in_one.features.play_with_maps

import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.utils.Constants.LOCATION_UPDATE_INTERVAL
import com.example.jetpack_compose_all_in_one.utils.Constants.MAP_POS_BIGBEN
import com.example.jetpack_compose_all_in_one.utils.bitmapDescriptorFromRes
import com.example.jetpack_compose_all_in_one.utils.requestAllLocation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import kotlinx.coroutines.delay

// Step 1- verify google map
//step 2-  add marker
//step 3 - customise marker
//step 4- add circle
//step 5- add polygon
// step 6 - add polyline
// step 7 - add current location feature
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ComposeDemoApp(
    vm: MapViewModel,
    getCurrentLocationFunc: ((Location?) -> Unit) -> Unit
) {
    val ctx = LocalContext.current
    val willDraw = rememberSaveable { mutableStateOf(false) }

    var isLocationAvailable by remember{ mutableStateOf(false) }
    var requestingLocation = requestAllLocation{ isLocationAvailable = it }
    var currentLocation by remember { mutableStateOf<Location?>(null) }
    val currentLocationMarker = remember{ derivedStateOf { MarkerState(
        if (currentLocation == null) MAP_POS_BIGBEN
        else LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
    ) } }

    LaunchedEffect(isLocationAvailable) {
        while (isLocationAvailable) {
            getCurrentLocationFunc{
                currentLocation = it

                val state = vm.cameraPositionState
                it?.let { loc ->
                    state.move(CameraUpdateFactory.newCameraPosition(
                        CameraPosition(
                            LatLng(loc.latitude, loc.longitude),
                            state.position.zoom,
                            state.position.tilt,
                            state.position.bearing
                        )
                    ))
                }
            }
            delay(LOCATION_UPDATE_INTERVAL)
        }
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                SimpleTextButton(
                    if (willDraw.value) "Ew hide this thing" else "Draw something"
                ) {
                    willDraw.value = !willDraw.value
                }

                SimpleTextButton("Mode: ${vm.mapTypeDisplayName}") { vm.changeMapType() }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                SimpleTextButton("Go to current position", enabled = !isLocationAvailable) {
                    requestingLocation.launchMultiplePermissionRequest()
                }

                SimpleTextButton("Nothing", enabled = false) {
                    // W.I.P.
                }
            }
        }

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            cameraPositionState = vm.cameraPositionState,
            properties = MapProperties(mapType = vm.mapType2.value, isMyLocationEnabled = isLocationAvailable)
        ) {
            Marker(
                state = currentLocationMarker.value,
                title = "Look at you",
                snippet = "What."
            )

            if (willDraw.value) {
                Marker(
                    state = MarkerState(position = MAP_POS_BIGBEN),
                    title = "Neo Armstrong Cyclone Jet Armstrong Cannon",
                    snippet = "It's really perfect.",
                    icon = bitmapDescriptorFromRes(ctx, R.drawable.cannon, dp_100, dp_50)
                )

                Circle(
                    center = LatLng(51.500749, -0.12425),
                    radius = 10.0, strokeColor = Color.Red
                )
                Circle(
                    center = LatLng(51.500749, -0.12485),
                    radius = 10.0, strokeColor = Color.Green
                )
                Polyline(
                    points = listOf(
                        LatLng(51.500749, -0.12440),
                        LatLng(51.501200, -0.12440)
                    ), color = Color.Blue
                )
                Polyline(
                    points = listOf(
                        LatLng(51.500749, -0.12470),
                        LatLng(51.501200, -0.12470)
                    ), color = Color.Yellow
                )
                Polygon(
                    points = listOf(
                        LatLng(51.501200, -0.12480),
                        LatLng(51.501200, -0.12430),
                        LatLng(51.501300, -0.12440),
                        LatLng(51.501300, -0.12470)
                    ),
                    fillColor = Color.Transparent,
                    strokeColor = Color.Black,
                    zIndex = 1.0f
                )
            }
        }
    }
}

/*
@Composable
private fun GoogleMapDynamic(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    mapType: Int,
    content: @Composable () -> Unit
) {
    when (mapType) {
        GoogleMap.MAP_TYPE_NORMAL -> {
            GoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.NORMAL),
                content = content
            )
        }

        GoogleMap.MAP_TYPE_SATELLITE -> {
            GoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.SATELLITE),
                content = content
            )
        }

        GoogleMap.MAP_TYPE_TERRAIN -> {
            GoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.TERRAIN),
                content = content
            )
        }

        GoogleMap.MAP_TYPE_HYBRID -> {
            GoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.HYBRID),
                content = content
            )
        }

        else -> {
            GoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = MapType.NONE),
                content = content
            )
        }
    }
}*/