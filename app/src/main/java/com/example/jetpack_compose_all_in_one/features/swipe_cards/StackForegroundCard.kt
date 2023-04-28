package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StackForegroundCard(
    modifier: Modifier = Modifier,
    state: SwipeableState,
    content: @Composable () -> Unit,
) {
    SwipeOverlay(state) {
        BoxWithConstraints(modifier, contentAlignment = Alignment.BottomCenter) {
            Card(Modifier.swipeable(state)) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    content()
                }
            }
            SwipeControls(constraints.maxWidth, state)
        }
    }
}