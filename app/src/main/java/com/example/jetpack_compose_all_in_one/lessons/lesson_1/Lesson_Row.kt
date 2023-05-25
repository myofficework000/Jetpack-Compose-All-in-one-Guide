package com.example.jetpack_compose_all_in_one.lessons.lesson_1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_all_in_one.ui.components.CustomSpacer
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.StyleableLessonText
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5

/**
 * Lesson about [Row]s
 *
 * * [Row] contains it's children in horizontal order.
 */
@Composable
fun LessonRow() {
    LazyColumn(Modifier.fillMaxSize()) {
        item {
            LessonHeader(text = "Row")
            StyleableLessonText(text = "**Row** is a layout composable that places its children in a horizontal sequence.")
            RowExample()
            CustomSpacer(height = dp_5)

            StyleableLessonText(
                text = "Padding order determines whether it's padding or margin for that component."
                        + "In example below check out paddings."
            )
            ColumnsAndRowPaddingsExample()
            CustomSpacer(height = dp_5)

            StyleableLessonText(text = "Shadow can be applied to Column or Row.")
            ShadowExample()
        }
    }
}
