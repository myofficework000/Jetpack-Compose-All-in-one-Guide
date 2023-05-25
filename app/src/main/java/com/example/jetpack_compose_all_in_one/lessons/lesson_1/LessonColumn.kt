package com.example.jetpack_compose_all_in_one.lessons.lesson_1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_all_in_one.ui.components.CustomSpacer
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.StyleableLessonText
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5


/**
 * Lesson about [Column]s
 *
 * * [Column] contains it's children in vertical order
 */
@Composable
fun LessonColumn() {
    LazyColumn(Modifier.fillMaxSize()) {

        item {

            LessonHeader(text = "Column")
            StyleableLessonText(text = "**Column** is a layout composable that places its children in a vertical sequence.")
            ColumnExample()
            CustomSpacer(height = dp_5)

            StyleableLessonText(text = "Shadow can be applied to Column or Row.")
            ShadowExample()
        }
    }
}
