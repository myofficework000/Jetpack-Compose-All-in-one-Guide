package com.example.jetpack_compose_all_in_one.lessons.lesson_5

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.lessons.lesson_5.viewmodels.MapSearchViewModel
import com.example.jetpack_compose_all_in_one.ui.components.AutoCompleteSearchBar
import com.example.jetpack_compose_all_in_one.ui.components.SimpleSearchBar
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Lesson_5_Chapter_4_Map_Search(vm: MapSearchViewModel) {
    LessonContent(vm)
}

@Composable
private fun LessonContent(vm: MapSearchViewModel) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            Constants.MAP_POS_BIGBEN,
            Constants.MAP_DEFAULT_ZOOM_1
        )
    }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            cameraPositionState = cameraPositionState
        ) {

        }

        AutoCompleteSearchBar(
            text = vm.searchText,
            suggestions = vm.searchResults.map { it.text },
            modifier = Modifier.fillMaxWidth().padding(dp_15),
            onChange = { vm.searchText = it; vm.searchPlace(it) },
            onSuggestionClick = { vm.searchText = vm.searchResults[it].text }
        ) {}
    }
}