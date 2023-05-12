package com.example.jetpack_compose_all_in_one.lessons.lesson_6

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.google.maps.android.compose.GoogleMap

@Preview()
@Composable
fun Lesson_6_Chapter_1_Google_Places_Search() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dp_15)
    )
}