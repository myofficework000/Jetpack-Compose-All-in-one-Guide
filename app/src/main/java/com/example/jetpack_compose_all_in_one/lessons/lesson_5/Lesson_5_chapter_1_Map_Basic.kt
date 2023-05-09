package com.example.jetpack_compose_all_in_one.lessons.lesson_5

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader

@Preview()
@Composable
fun Lesson_5_Chapter_Map_Basic() {
    LessonContent()
}

@Composable
private fun LessonContent() {

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            LessonHeader(text = "Simple Map")
            MapSimpleUI()
            LessonHeader(text = "Inflate Marker On Map")
            InflateMarkerOnMap()
            LessonHeader(text = "Customize Marker On Map")
            CustomMarkerOnMap()
            LessonHeader(text = "Create PolyLine on Map")
            CreatePolyLineOnMap()
            LessonHeader(text = "Create Polygon on Map")
            CreatePolygonOnMap()
            LessonHeader(text = "Create Circle on Map")
            CreateCircleOnMap()
        }
    }
}

@Composable
fun MapSimpleUI() {

}

@Composable
fun InflateMarkerOnMap() {

}

@Composable
fun CustomMarkerOnMap() {

}


@Composable
fun CreatePolyLineOnMap() {

}

@Composable
fun CreatePolygonOnMap() {

}

@Composable
fun CreateCircleOnMap() {

}