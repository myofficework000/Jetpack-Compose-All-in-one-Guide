package com.example.jetpack_compose_all_in_one.lessons.lesson_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.AnimatedProgressBar
import com.example.jetpack_compose_all_in_one.ui.components.CustomProgress
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.LinearProgress
import com.example.jetpack_compose_all_in_one.ui.components.SimpleProgress
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager

@Preview
@Composable
fun Lesson_2_Chapter_5_Progressbar() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 4,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.l2c5_header_text)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            when (currentPage.value) {
                0 -> SimpleProgress()
                1 -> CustomProgress()
                2 -> LinearProgress()
                3 -> AnimatedProgressBar()
            }
        }
    }
}