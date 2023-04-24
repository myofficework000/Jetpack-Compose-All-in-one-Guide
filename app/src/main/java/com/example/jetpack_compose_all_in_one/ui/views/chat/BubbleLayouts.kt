package com.example.jetpack_compose_all_in_one.ui.views.chat

import com.example.jetpack_compose_all_in_one.ui.views.chat.bubble.BubbleState
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_all_in_one.ui.views.chat.bubble.drawBubble
import com.example.jetpack_compose_all_in_one.ui.views.chat.bubble.drawBubbleWithShape

@Composable
fun BubbleLayout(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {
    Column(
        modifier.drawBubble(bubbleState)
    ) {
        content()
    }
}

@Composable
fun BubbleLayoutWithShape(
    modifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {
    Column(
        modifier.drawBubbleWithShape(bubbleState)
    ) {
        content()
    }
}