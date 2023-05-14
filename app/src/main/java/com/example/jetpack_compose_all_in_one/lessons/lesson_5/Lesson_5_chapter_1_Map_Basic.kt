package com.example.jetpack_compose_all_in_one.lessons.lesson_5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.LogicPager
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

@Preview
@Composable
fun Lesson_5_Chapter_Map_Basic() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            Constants.MAP_POS_BIGBEN,
            Constants.MAP_DEFAULT_ZOOM_1
        )
    }
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 6,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.l5c1_header_text)[currentPage.value],
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
                when (currentPage.value) {
                    0 -> {}
                    1 -> InflateMarkerOnMap()
                    2 -> CustomMarkerOnMap()
                    3 -> CreatePolyLineOnMap()
                    4 -> CreatePolygonOnMap()
                    5 -> CreateCircleOnMap()
                }
            }
        }
    }
}

@Composable
private fun InflateMarkerOnMap() {
    Marker(
        state = MarkerState(position = Constants.MAP_POS_BIGBEN),
        title = "Regular marker",
        snippet = "This is a regular marker."
    )
}

@Composable
private fun CustomMarkerOnMap() {
    Marker(
        state = MarkerState(position = Constants.MAP_POS_BIGBEN),
        title = "Custom Marker",
        snippet = "This is a marker with custom bitmap.",
        icon = bitmapDescriptorFromRes(LocalContext.current, R.drawable.cannon, dp_100, dp_50)
    )
}


@Composable
private fun CreatePolyLineOnMap() {
    Polyline(
        points = listOf(
            LatLng(
                Constants.MAP_POS_BIGBEN.latitude + 0.0002,
                Constants.MAP_POS_BIGBEN.longitude + 0.0003
            ),
            LatLng(
                Constants.MAP_POS_BIGBEN.latitude - 0.0002,
                Constants.MAP_POS_BIGBEN.longitude - 0.0003
            )
        )
    )
    Polyline(
        points = listOf(
            LatLng(
                Constants.MAP_POS_BIGBEN.latitude + 0.0002,
                Constants.MAP_POS_BIGBEN.longitude - 0.0003
            ),
            LatLng(
                Constants.MAP_POS_BIGBEN.latitude - 0.0002,
                Constants.MAP_POS_BIGBEN.longitude + 0.0003
            )
        )
    )
}

@Composable
private fun CreatePolygonOnMap() {
    Polygon(
        points = listOf(
            LatLng(
                Constants.MAP_POS_BIGBEN.latitude + 0.0002,
                Constants.MAP_POS_BIGBEN.longitude
            ),
            LatLng(
                Constants.MAP_POS_BIGBEN.latitude - 0.0002,
                Constants.MAP_POS_BIGBEN.longitude - 0.0003
            ),
            LatLng(
                Constants.MAP_POS_BIGBEN.latitude - 0.0002,
                Constants.MAP_POS_BIGBEN.longitude + 0.0003
            )
        ),
        fillColor = Color.Transparent,
        strokeColor = Color.Black,
        zIndex = 1.0f
    )
}

@Composable
private fun CreateCircleOnMap() {
    Circle(
        center = Constants.MAP_POS_BIGBEN,
        radius = 10.0
    )
}