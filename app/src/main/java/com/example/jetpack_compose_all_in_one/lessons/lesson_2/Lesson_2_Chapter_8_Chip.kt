package com.example.jetpack_compose_all_in_one.lessons.lesson_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun Lesson_2_Chapter_8_Chip() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 3,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.l2c8_header_text)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            when (currentPage.value) {
                0 -> ChipDemo1()
                1 -> ChipDemo2()
                2 -> ChipDemo3()
            }
        }
    }
}

@Composable
fun ChipDemo3() {
    //Chi
}

@Composable
fun ChipDemo2() {
    //Urvish
}

enum class Chips(selected: Boolean){
    None(false),
    Work(false),
    Class(false),
    Casual(false),
    Hobby(selected = false)
}

fun getListOfChips(): List<Chips> = listOf(Chips.None, Chips.Work, Chips.Class, Chips.Casual, Chips.Hobby)

@Preview()
@Composable
fun ChipDemo1(
    name: String = "Dummy",
    isSelected: Boolean = false,
onChipSelected: () -> Unit = {}) {



    val selectedChip by rememberSaveable {
        mutableStateOf(Chips.None)
    }

    LazyRow{
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomChip(name: String = "Dummy",isSelected: Boolean = false,
               onChipSelected: () -> Unit = {}){
    Chip(onClick = { onChipSelected.invoke() },

        ) {
        Text(text = name, style = MaterialTheme.typography.bodyMedium)
    }
}
