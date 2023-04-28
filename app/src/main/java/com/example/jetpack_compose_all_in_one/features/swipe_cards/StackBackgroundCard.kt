package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun StackBackgroundCard(
    modifier: Modifier = Modifier,
    state: SwipeableState,
    content: @Composable () -> Unit,
) = with(state) {
    Card(modifier.graphicsLayer {
        scaleX = animatedScale.value
        scaleY = animatedScale.value
    }) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content()
        }
    }
}