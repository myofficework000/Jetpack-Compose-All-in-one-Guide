package com.example.jetpack_compose_all_in_one.features.play_with_maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.utils.bitmapDescriptorFromRes
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

// Step 1- verify google map
//step 2-  add marker
//step 3 - customise marker
//step 4- add circle
//step 5- add polygon
// step 6 - add polyline
// step 7 - add current location feature
@Composable
fun ComposeDemoApp() {
    val bigben = LatLng(51.5007761,-0.124546)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bigben, 18.54f)
    }
    val ctx = LocalContext.current

    Column(
        Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = bigben),
                title = "Neo Armstrong Cyclone Jet Armstrong Cannon",
                snippet = "It's really perfect.",
                icon = bitmapDescriptorFromRes(ctx, R.drawable.cannon, dp_100, dp_50)
            )
            
            Circle(center = LatLng(51.500749, -0.12425),
                radius = 10.0, strokeColor = Color.Red)
            Circle(center = LatLng(51.500749, -0.12485),
                radius = 10.0, strokeColor = Color.Green)
            Polyline(points = listOf(
                LatLng(51.500749, -0.12440),
                LatLng(51.501200, -0.12440)
            ), color = Color.Blue)
            Polyline(points = listOf(
                LatLng(51.500749, -0.12470),
                LatLng(51.501200, -0.12470)
            ), color = Color.Yellow)
            Polygon(points = listOf(
                LatLng(51.501200, -0.12480),
                LatLng(51.501200, -0.12430),
                LatLng(51.501300, -0.12440),
                LatLng(51.501300, -0.12470)
            ),
                fillColor = Color.Transparent,
                strokeColor = Color.Black,
                zIndex = 1.0f)
        }

        Row(
            Modifier.padding(16.dp)
        ) {
            SimpleTextButton("Draw something") {

            }

            SimpleTextButton("Go to current position") {
                // W.I.P.
            }
        }
    }




}