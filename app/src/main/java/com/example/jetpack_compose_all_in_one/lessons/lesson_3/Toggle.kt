package com.example.jetpack_compose_all_in_one.lessons.lesson_3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ToggleList() {
    var checked by remember { mutableStateOf(false) }
    val lists: List<String> = listOf("A", "B", "C", "D")
    Column {
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
        if (checked) {
            GridView(lists)
        } else {
            ListView(lists)
        }
    }
}

@Composable
fun GridView(lists: List<String>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
        content = {
            items(lists) {
                Text(
                    text = it,
                    modifier = Modifier.padding(40.dp)
                )
            }
        }
    )
}

@Composable
fun ListView(lists: List<String>) {
    LazyColumn(
        content = {
            items(lists) {
                Text(
                    text = it,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    )
}