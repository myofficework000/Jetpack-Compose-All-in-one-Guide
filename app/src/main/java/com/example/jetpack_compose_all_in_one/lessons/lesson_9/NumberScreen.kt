package com.example.jetpack_compose_all_in_one.lessons.lesson_9

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun NumberScreen(){
    Column {
        LazyRowNumbers()
        LazyColumnNumbers()
    }
}

@Composable
fun LazyRowNumbers(){
    LazyRow(modifier = Modifier.testTag("lazyRow")) {
        items(10) { index ->
            Text(
                text = "Item $index",
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun LazyColumnNumbers(){
    val nums = 10
    val clickedNums = remember { mutableStateListOf<Int>() }
    LazyColumn(modifier = Modifier.testTag("lazyColumn")) {
        items(nums) { index ->
            val text = if (index in clickedNums) "Clicked: Item $index" else "Item $index"
            val backgroundColor = if (index in clickedNums) Color.Red else Color.Transparent
            Text(
                text = text,
                modifier = Modifier
                    .padding(10.dp)
                    .background(backgroundColor)
                    .clickable {
                        if (index !in clickedNums) {
                            clickedNums.add(index)
                        }
                    }
                    .testTag("item$index")
            )
        }
    }
}