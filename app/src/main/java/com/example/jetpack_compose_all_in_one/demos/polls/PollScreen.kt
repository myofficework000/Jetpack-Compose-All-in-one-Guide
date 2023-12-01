package com.example.jetpack_compose_all_in_one.demos.polls

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun PollScreen(viewModel: QuizViewModel) {
    val radioOptions = listOf("Mango","Banana","Apple","Peach")
    var selectedOption by remember { mutableStateOf(radioOptions[0]) }
    val attemptedTime = viewModel.attemptedTime.observeAsState()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (questionStatement, attemptTime, radioGroup, submitButton) = createRefs()

        Text(text = "Question: Question statement here",
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(questionStatement) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )

        Text(text = "attempted ${attemptedTime.value} times",
            modifier = Modifier
                .padding(5.dp)
                .constrainAs(attemptTime) {
                    top.linkTo(questionStatement.bottom)
                    end.linkTo(parent.end)
                })

        Column(modifier = Modifier.constrainAs(radioGroup){
            top.linkTo(attemptTime.bottom, 20.dp)
            start.linkTo(parent.start,20.dp)
        }) {
            radioOptions.forEach { fruitName ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (fruitName == selectedOption),
                        onClick = { selectedOption = fruitName }
                    )
                    Text(
                        text = fruitName,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        Button(onClick = { viewModel.submitPoll() }, modifier = Modifier.constrainAs(submitButton){
            top.linkTo(radioGroup.bottom,50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text("Submit")
        }
    }
}