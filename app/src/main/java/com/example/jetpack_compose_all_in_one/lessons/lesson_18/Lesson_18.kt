package com.example.jetpack_compose_all_in_one.lessons.lesson_18

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager
import com.example.jetpack_compose_all_in_one.utils.PagedLessonHeader
import kotlinx.coroutines.delay

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
                0 -> ComposeUITestForInputField { _, _ ->

                }

                1 -> {
                    val stateList = rememberSaveable {
                        mutableStateListOf<String>()
                    }
                    stateList.add("One")
                    stateList.add("Two")
                    stateList.add("Three")
                    stateList.add("Four")
                    stateList.add("Five")
                    ComposeUITestForListScreen(list = stateList)
                }
            }
        }
    }
}

@Composable
fun ComposeUITestForInputField(onclick: (String, String) -> Unit) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter email") })
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Enter password") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { onclick(email, password) }) {
            Text(text = "Submit")
        }

    }
}


@Composable
fun ComposeUITestForListScreen(list: SnapshotStateList<String>) {

    LazyColumn {
        items(list) { item ->
            Text(text = item)
        }
    }
}