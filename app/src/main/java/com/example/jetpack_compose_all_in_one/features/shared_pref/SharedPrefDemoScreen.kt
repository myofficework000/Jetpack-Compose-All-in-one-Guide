package com.example.jetpack_compose_all_in_one.features.shared_pref

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.shared_pref.demo1.Demo1
import com.example.jetpack_compose_all_in_one.features.shared_pref.demo2.Demo2
import com.example.jetpack_compose_all_in_one.lessons.lesson_1.LessonBox
import com.example.jetpack_compose_all_in_one.lessons.lesson_1.LessonColumn
import com.example.jetpack_compose_all_in_one.lessons.lesson_1.LessonRow
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager
import com.example.jetpack_compose_all_in_one.utils.PagedLessonHeader

@Preview
@Composable
fun SharedPrefDemoScreen() {
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
            PagedLessonHeader(
                modifier = Modifier.fillMaxWidth().padding(dp_15),
                currentPage = currentPage.value,
                headers = stringArrayResource(R.array.lesson_1_header_text).toList(),
                infoContent = listOf(
                    "Dis a row",
                    "That's a column",
                    "https://developer.android.com/jetpack/compose/layouts/basics"
                )
            )

            when (currentPage.value) {
                0 -> Demo1()
                1 -> Demo2()
            }
        }
    }
}
