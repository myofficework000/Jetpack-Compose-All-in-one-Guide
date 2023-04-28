package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> Stack(
    modifier: Modifier = Modifier,
    items: List<T>,
    state: SwipeableState,
    itemContent: @Composable (T) -> Unit,
) = with(state) {
    items.getOrNull(1)?.takeIf { it !== items.firstOrNull() }?.let { profile ->
        StackBackgroundCard(modifier, state) {
            itemContent(profile)
        }
    }
    items.firstOrNull()?.let { profile ->
        StackForegroundCard(modifier, state) {
            itemContent(profile)
        }
    }
}