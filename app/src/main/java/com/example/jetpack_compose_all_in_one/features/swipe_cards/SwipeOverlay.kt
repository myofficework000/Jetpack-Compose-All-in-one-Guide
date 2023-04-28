package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun SwipeOverlay(state: SwipeableState, content: @Composable () -> Unit) = with(state) {
    Box(contentAlignment = Alignment.Center) {
        content()
        Box(Modifier, contentAlignment = Alignment.Center) {
            when {
                isSwipingToLeft || direction == SwipeDirection.LEFT -> Icon(
                    Icons.Rounded.Clear,
                    "swipe left",
                    Modifier.fillMaxSize(.25f).graphicsLayer {
                        alpha = animatedAlpha.value
                    },
                    tint = if (isSwipingToLeft) Color.Red.copy(alpha = .5f) else Color.Unspecified)
                isSwipingToRight || direction == SwipeDirection.RIGHT -> Icon(
                    Icons.Rounded.Favorite,
                    "swipe right",
                    Modifier.fillMaxSize(.25f).graphicsLayer {
                        alpha = animatedAlpha.value
                    },
                    tint = if (isSwipingToRight) Color.Red.copy(alpha = .5f) else Color.Unspecified)
                else -> Unit
            }
        }
    }
}