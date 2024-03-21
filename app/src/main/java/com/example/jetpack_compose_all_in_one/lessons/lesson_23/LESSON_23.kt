package com.example.jetpack_compose_all_in_one.lessons.lesson_23

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager
import com.example.jetpack_compose_all_in_one.utils.PagedLessonHeader

/**
 * Composable function for Lesson 23.
 * It displays the content of Lesson 23.
 */
@Composable
@Preview(showBackground = true)
fun Lesson_23() {
    LessonContent() // Calls the LessonContent composable function to display Lesson 23 content.
}

/**
 * Composable function for the content of Lesson 23.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LessonContent() {
    // Remember the current page using mutable state
    val currentPage = rememberSaveable { mutableIntStateOf(0) }

    // LogicPager composable for handling paging
    LogicPager(
        pageCount = 2,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it) // Apply padding to the column based on the pager state
        ) {
            // Display the header for the paged lesson
            PagedLessonHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dp_15), // Apply padding to the header
                currentPage = currentPage.intValue,
                headers = stringArrayResource(R.array.lesson_23_header_text).toList(), // Get headers from string resource
                infoContent = listOf(
                    "1",
                    "2",
                    "3",
                    "4",
                    "https://www.google.com"
                ) // Information content for the header
            )

            // Based on the current page, display different tab screens
            when (currentPage.intValue) {
                0 -> TabScreen() // Display TabScreen for page 0
                1 -> TabScreen() // Display TabScreen for page 1
            }
        }
    }
}
