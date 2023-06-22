package com.example.jetpack_compose_all_in_one.lessons.lesson_13

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8

@Composable
fun Lesson_13(){
    LessonContent()
}

@Composable
fun LessonContent(){

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.padding(dp_8))
        Plurals(2)
    }

}

@Composable
fun Plurals(itemCount: Int) {
    val resources = LocalContext.current.resources
    val quantityString = resources.getQuantityString(
         R.plurals.item_count,
        itemCount,
        arrayOf(itemCount)
    )

    Text(
        text = quantityString,
        style = MaterialTheme.typography.body1
    )
}