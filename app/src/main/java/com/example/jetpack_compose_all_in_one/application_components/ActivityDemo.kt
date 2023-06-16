package com.example.jetpack_compose_all_in_one.application_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager


@Preview
@Composable
fun ActivityDemo() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 2,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.activity_demo_header)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            when (currentPage.value) {
                0 -> OpenActivity()
                1 -> CloseActivity()
            }
        }
    }
}

@Composable
fun CloseActivity() {
    Column {
        OutlinedButton(
            onClick = {}
        ) {
            Text(text = "onCreate")
        }

        OutlinedButton(
            onClick = {}
        ) {
            Text(text = "onStart")
        }

        OutlinedButton(
            onClick = {}
        ) {
            Text(text = "onResume")
        }
    }
}

@Composable
fun OpenActivity() {
    Column {
        OutlinedButton(
            onClick = {}
        ) {
            Text(text = "onPause")
        }

        OutlinedButton(
            onClick = {}
        ) {
            Text(text = "onStop")
        }

        OutlinedButton(
            onClick = {}
        ) {
            Text(text = "onDestroy")
        }
    }
}
