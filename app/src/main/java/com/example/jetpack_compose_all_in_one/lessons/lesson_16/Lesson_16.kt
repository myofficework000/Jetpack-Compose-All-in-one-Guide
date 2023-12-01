package com.example.jetpack_compose_all_in_one.lessons.lesson_16

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun Lesson_16() {
    var t1 by rememberSaveable { mutableStateOf("T1") }
    var t2 by rememberSaveable { mutableStateOf("T2") }
    var t3 by rememberSaveable { mutableStateOf("T3") }
    var visible by rememberSaveable { mutableStateOf(true) }
    Column(
        Modifier
            .fillMaxSize()
            .height(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(80.dp)
            ) {
                Box(Modifier.fillMaxSize()) {
                    if (visible) {
                        Text(
                            text = t1,
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(80.dp)
            ) {
                Box(Modifier.fillMaxSize()) {
                    if (visible) {
                        Text(
                            text = t2,
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(80.dp)
            ) {
                Box(Modifier.fillMaxSize()) {
                    if (visible) {
                        Text(
                            text = t3,
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(onClick = { t1 = "Text 1" }, Modifier.align(Alignment.TopStart)) {
                Text(text = "button 1")
            }
            Button(onClick = { t2 = "Text 2" }, Modifier.align(Alignment.TopCenter)) {
                Text(text = "button 2")
            }
            Button(onClick = { t3 = "Text 3" }, Modifier.align(Alignment.TopEnd)) {
                Text(text = "button 3")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Button(onClick = { visible = false }, Modifier.align(Alignment.TopStart)) {
                Text(text = "Hide")
            }
            Button(onClick = { visible = true }, Modifier.align(Alignment.TopEnd)) {
                Text(text = "Show")
            }
        }
    }
}