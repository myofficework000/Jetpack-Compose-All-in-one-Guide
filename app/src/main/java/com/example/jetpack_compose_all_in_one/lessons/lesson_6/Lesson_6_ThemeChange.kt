package com.example.jetpack_compose_all_in_one.lessons.lesson_6

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme

@Preview
@Composable
fun Lesson_6_ThemeChange() {
    ChangeAppTheme()
}

@Composable
fun ChangeAppTheme() {
    val isDarkTheme = remember { mutableStateOf(true) }
    JetpackComposeAllInOneTheme(darkTheme = isDarkTheme.value) {
        Switch(
            checked = isDarkTheme.value,
            onCheckedChange = {
                isDarkTheme.value = it
            }
        )
    }
}
