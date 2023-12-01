package com.example.jetpack_compose_all_in_one.demos.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun QuizScreen() {
    var submitted by rememberSaveable { mutableStateOf(false) }
    Column(Modifier.fillMaxSize().padding(8.dp)) {
        Text(text = "Questions")
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .height(450.dp)
        ) {
            items(getQuizzes()) {
                QuizItem(
                    item = it,
                    submitted = submitted,
                    doOver = { doOver -> submitted = doOver })
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
        Button(onClick = { submitted = true }) {
            Text(text = "Submit")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun QuizItem(
    modifier: Modifier = Modifier,
    item: Quiz = Quiz(
        id = 1,
        answer = "Triangle",
        options = listOf("Triangle", "Circle", "Rectangle", "None above"),
        statement = "Which of the following shapes has the feature of stability?"
    ),
    submitted: Boolean = false,
    doOver: (Boolean) -> Unit = {}
) {
    var selectedOption by rememberSaveable { mutableStateOf("") }

    Card {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(text = item.statement)
            item.options.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = {
                            selectedOption = option
                            doOver(false)
                        }
                    )
                    Text(
                        text = option,
                        modifier = Modifier.padding(start = 8.dp),
                        color = if (!submitted) Color.Black
                        else if (item.answer != option && option != selectedOption) Color.Black
                        else if (item.answer == option) Color.Green
                        else Color.Red
                    )
                }
            }
        }
    }
}


fun getQuizzes(): List<Quiz> {
    return listOf(
        Quiz(
            id = 1,
            answer = "Triangle",
            options = listOf("Triangle", "Circle", "Rectangle", "None above"),
            statement = "Which of the following shapes has the feature of stability?"
        ),
        Quiz(
            id = 2,
            answer = "Apple",
            options = listOf("Potato", "Pumpkin", "Apple", "None above"),
            statement = "Which of the following is fruit?"
        )
    )
}