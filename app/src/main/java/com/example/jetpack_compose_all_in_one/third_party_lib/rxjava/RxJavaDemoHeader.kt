package com.example.jetpack_compose_all_in_one.third_party_lib.rxjava

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader

// Just for convenience.
@Composable
fun RxJavaDemoHeader(
    text: String
) {
    Box(
        Modifier.fillMaxWidth().padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) { LessonHeader(text = text, textAlign = TextAlign.Center) }
}