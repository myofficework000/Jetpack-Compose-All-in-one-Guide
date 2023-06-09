package com.example.jetpack_compose_all_in_one.lessons.lesson_11

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Lesson_11() {
    LessonContent(context = LocalContext.current)
}

@Composable
fun LessonContent(context: Context){
    val intent = Intent(context, ComposeWithOldWayActivityExample::class.java)
    context.startActivity(intent)
}
