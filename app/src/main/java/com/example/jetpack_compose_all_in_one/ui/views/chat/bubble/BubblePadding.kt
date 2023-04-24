package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Stable
data class BubblePadding internal constructor(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp
)

fun Padding(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp
): BubblePadding {
    return BubblePadding(start, top, end, bottom)
}

fun Padding(
    all: Dp = 0.dp
): BubblePadding {
    return BubblePadding(all, all, all, all)
}

fun Padding(
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp
): BubblePadding {
    return BubblePadding(horizontal, vertical, horizontal, vertical)
}