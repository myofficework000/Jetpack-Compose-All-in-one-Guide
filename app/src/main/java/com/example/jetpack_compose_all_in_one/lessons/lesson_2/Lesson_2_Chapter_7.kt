package com.example.jetpack_compose_all_in_one.lessons.lesson_2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.utils.model.getLanguages

@Preview
@Composable
fun Lesson_2_Chapter_7_RadioButton() {
    LessonContent()
}

@Composable
private fun LessonContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text( text = stringResource(id = R.string.select_gender))
        RadioButtonDemo()

        Text(text = stringResource(id = R.string.select_your_language))
        CheckBoxDemo(getLanguages())
    }
}

@Composable
fun CheckBoxDemo(list: List<String>) {
    val selectedLanguages = remember { mutableStateListOf<String>() }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        list.forEach{
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Checkbox(
                    checked = selectedLanguages.contains(it),
                    onCheckedChange = {isChecked ->
                        if(isChecked){
                            selectedLanguages.add(it)
                        }else{
                            selectedLanguages.remove(it)
                        }
                    }
                )
                Text(text = it)
            }
        }
    }
}

@Composable
fun RadioButtonDemo() {
    var selectedOption by remember {mutableStateOf("Male")}
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ){
            RadioButton(
                selected = selectedOption == "Male",
                onClick = {selectedOption = "Male"}
            )
            Text(
                text = "Male",
            textAlign = TextAlign.Center)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        ){
            RadioButton(
                selected = selectedOption == "Female",
                onClick = {selectedOption = "Female"}
            )
            Text(
                text = "Female",
                textAlign = TextAlign.Center)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ){
            RadioButton(
                selected = selectedOption == "Other",
                onClick = {selectedOption = "Other"}
            )
            Text(
                text = "Other",
                textAlign = TextAlign.Center)
        }
    }
}


