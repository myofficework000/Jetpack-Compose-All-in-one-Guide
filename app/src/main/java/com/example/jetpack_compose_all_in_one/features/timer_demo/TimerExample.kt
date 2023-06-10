package com.example.jetpack_compose_all_in_one.features.timer_demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerExample(){
    var isButtonClicked by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf(0) }
    var job: Job? by remember { mutableStateOf(null) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        if (!isButtonClicked) {
            TextButton(
                onClick = { isButtonClicked = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Click Me")
            }
        } else {
            Text("Timer: $time seconds")
        }
    }

    LaunchedEffect(isButtonClicked) {
        if (isButtonClicked) {
            job = coroutineScope.launch {
                while (isButtonClicked) {
                    delay(1000)
                    time++
                }
            }
        } else {
            job?.cancel()
            time = 0
        }
    }
}