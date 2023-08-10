package com.example.jetpack_compose_all_in_one.lessons.lesson_14

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16

@Composable
fun Lesson_14() {
    LessonContent()
}

@Composable
fun LessonContent() {
    val entry1 = Pair("Key1", "USA")
    val entry2 = Pair("Key2", "Canada")
    val entry3 = Pair("Key3", "UK")
    val entry4 = Pair("Key4", "India")
    val entry5 = Pair("Key4", "Qatar")

    var showSelected by remember { mutableStateOf(false) }
    var selectedEntry by remember { mutableStateOf(Pair("", "")) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpinnerSample(
            listOf(entry1, entry2, entry3, entry4, entry5),
            preselected = entry2,
            onSelectionChanged = { selected ->
                showSelected = true
                selectedEntry = Pair(first = selected.first, second = selected.second)
            }
        )

        if (showSelected) {
            Text(
                text = "Key: ${selectedEntry.first}\nValue: ${selectedEntry.second}",
                fontSize = sp_16,
                fontWeight = FontWeight.Bold
            )
        }
    }


}

@Composable
fun SpinnerSample(
    list: List<Pair<String, String>>,
    preselected: Pair<String, String>,
    onSelectionChanged: (myData: Pair<String, String>) -> Unit,
    modifier: Modifier = Modifier
) {

    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) } // initial value

    OutlinedCard(
        modifier = modifier.clickable {
            expanded = !expanded
        }
    ) {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {

            Text(
                text = selected.second,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = dp_15, vertical = dp_8)
            )
            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(dp_8))

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                list.forEach { listEntry ->

                    DropdownMenuItem(
                        onClick = {
                            selected = listEntry
                            expanded = false
                            onSelectionChanged(selected)
                        },
                        text = {
                            Text(
                                text = listEntry.second,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                            )
                        },
                    )
                }
            }

        }
    }
}

