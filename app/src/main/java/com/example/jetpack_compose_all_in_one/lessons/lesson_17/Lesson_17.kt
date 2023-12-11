package com.example.jetpack_compose_all_in_one.lessons.lesson_17

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager
import com.example.jetpack_compose_all_in_one.utils.PagedLessonHeader

@Composable
@Preview(showBackground = true)
fun Lesson_17() {
    LessonContent()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LessonContent() {
    val currentPage = rememberSaveable { mutableIntStateOf(0) }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                currentPage = currentPage.intValue,
                headers = stringArrayResource(R.array.lesson_17_header_text).toList(),
                infoContent = listOf(
                    "1",
                    "2",
                    "3",
                    "4",
                    "https://www.google.com"
                )
            )

            when (currentPage.intValue) {
                0 -> FlowRowSimpleUsageExample()
                1 -> FlowColumnSimpleUsageExample()
            }
        }
    }
}

@ExperimentalLayoutApi
@Composable
private fun FlowRowSimpleUsageExample() {
    FlowRow(modifier = Modifier.padding(8.dp)) {
        Chip("Price: High to Low")
        Chip("Avg rating: 4+")
        Chip("Free breakfast")
        Chip("Free cancellation")
        Chip("£50 pn")
    }
}

@ExperimentalLayoutApi
@Composable
private fun FlowColumnSimpleUsageExample() {
    FlowColumn(modifier = Modifier.padding(8.dp)) {
        Chip("Price: High to Low")
        Chip("Avg rating: 4+")
        Chip("Free breakfast")
        Chip("Free cancellation")
        Chip("£50 pn")
    }
}

@Composable
private fun Chip(name: String) {
    Button(
        onClick = { /* Handle chip click */ },
        modifier = Modifier
            .padding(4.dp)
            .selectable(selected = false, onClick = {})
            .background(Color.White)
    ) {
        Text(text = name)
    }
}