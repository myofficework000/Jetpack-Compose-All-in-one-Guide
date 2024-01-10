package com.example.jetpack_compose_all_in_one.lessons.lesson_3

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
import com.example.jetpack_compose_all_in_one.ui.components.CustomVerticalList
import com.example.jetpack_compose_all_in_one.ui.components.ExpandableAnimatedList
import com.example.jetpack_compose_all_in_one.ui.components.ExpandableCardList
import com.example.jetpack_compose_all_in_one.ui.components.HorizontalSimpleList
import com.example.jetpack_compose_all_in_one.ui.components.ShimmerAnimatedList
import com.example.jetpack_compose_all_in_one.ui.components.SimpleHorizontalGridList
import com.example.jetpack_compose_all_in_one.ui.components.SimpleVerticalGridList
import com.example.jetpack_compose_all_in_one.ui.components.VerticalSimpleList
import com.example.jetpack_compose_all_in_one.ui.components.getCountries
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager
import com.example.jetpack_compose_all_in_one.utils.PagedLessonHeader
import com.example.jetpack_compose_all_in_one.utils.model.getNumbers

@Preview
@Composable
fun Lesson_3_Chapter_ListTypes() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 10,
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
                headers = stringArrayResource(R.array.lesson_3_header_text).toList(),
                infoContent = listOf(
                    "1",
                    "2",
                    "3",
                    "4",
                    "https://www.google.com"
                )
            )

            when (currentPage.value) {
                0 -> VerticalSimpleList(getCountries())
                1 -> CustomVerticalList(getCountries())
                2 -> HorizontalSimpleList(getCountries())
                3 -> SimpleVerticalGridList(list = getNumbers(), numOfColumns = 2)
                4 -> SimpleHorizontalGridList(list = getNumbers(), numOfRows = 2)
                5 -> ExpandableCardList(getCountries())
                6 -> ExpandableAnimatedList(getCountries())
                7 -> ShimmerAnimatedList()
                8 -> ToggleList()
                9 -> SwipeToDeleteScreen()
            }
        }
    }
}
