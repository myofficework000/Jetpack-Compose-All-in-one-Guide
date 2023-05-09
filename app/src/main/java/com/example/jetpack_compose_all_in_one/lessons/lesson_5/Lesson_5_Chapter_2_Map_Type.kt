package com.example.jetpack_compose_all_in_one.lessons.lesson_5

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader

@Preview()
@Composable
fun Lesson_5_Chapter_2_Map_Type() {
    LessonContent()
}

@Composable
private fun LessonContent() {

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            LessonHeader(text = "Map type Normal")
            MapTypeNormal()

            LessonHeader(text = "Map type Hybrid")
            MapTypeHybrid()

            LessonHeader(text = "Map type Terrain")
            MapTypeTerrain()

            LessonHeader(text = "Map type Satellite")
            MapTypeSatellite()

            LessonHeader(text = "Map type None")
            MapTypeNone()
        }
    }
}

@Composable
fun MapTypeHybrid() {

}

@Composable
fun MapTypeTerrain() {

}

@Composable
fun MapTypeNormal() {

}

@Composable
fun MapTypeSatellite() {

}

@Composable
fun MapTypeNone() {

}

