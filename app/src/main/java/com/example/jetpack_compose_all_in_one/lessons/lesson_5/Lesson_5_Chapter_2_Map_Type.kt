package com.example.jetpack_compose_all_in_one.lessons.lesson_5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.LogicPager
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Preview()
@Composable
fun Lesson_5_Chapter_2_Map_Type() {
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
    val mapType = remember {
        derivedStateOf {
            when (currentPage.value) {
                1 -> MapType.NORMAL
                2 -> MapType.SATELLITE
                3 -> MapType.HYBRID
                4 -> MapType.NONE
                else -> MapType.TERRAIN
            }
        }
    }

    LogicPager(
        pageCount = 5,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)) {
            LessonHeader(
                stringArrayResource(R.array.l5c2_header_text)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(mapType = mapType.value)
            )
        }
    }
}