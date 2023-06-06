package com.example.jetpack_compose_all_in_one.lessons.lesson_9

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.lessons.lesson_9.CounterHelper.processInput
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15

@Preview
@Composable
fun CounterDisplay() {

    var editedText by remember {
        mutableStateOf("")
    }

    var counterText by remember {
        mutableStateOf("Start copying")
    }

    Column(
        modifier = Modifier.padding(dp_15),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.testTag("Counter Display"),
            text = counterText,
            style = TextStyle(
                fontSize = 36.sp,
                color = Color.White
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("Input"),
            value = editedText,
            onValueChange = {
                editedText = it
            },
            label = {
                Text("Input")
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                counterText = processInput(editedText)
            }
        ) {
            Text(
                text = "Copy",
                style = TextStyle(
                    fontSize = 26.sp,
                    color = Color.White
                )
            )
        }
    }
}
