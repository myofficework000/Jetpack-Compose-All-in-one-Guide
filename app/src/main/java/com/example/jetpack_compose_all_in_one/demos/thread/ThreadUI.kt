package com.example.jetpack_compose_all_in_one.demos.thread

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun ThreadUI(){
    Column(Modifier.fillMaxSize()) {
        ThreadCommentSection()
    }
}