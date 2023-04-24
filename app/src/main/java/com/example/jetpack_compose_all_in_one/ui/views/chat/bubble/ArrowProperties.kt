package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class BubbleCornerRadius(
    var topLeft: Dp = 0.dp,
    var topRight: Dp = 0.dp,
    var bottomLeft: Dp = 0.dp,
    var bottomRight: Dp = 0.dp,
)

enum class ArrowShape {
    TRIANGLE_RIGHT,
    TRIANGLE_ISOSCELES,
    CURVED
}


enum class ArrowAlignment {
    None,
    LeftTop,
    LeftCenter,
    LeftBottom,
    RightTop,
    RightCenter,
    RightBottom,
    BottomLeft,
    BottomCenter,
    BottomRight,
    TopLeft,
    TopCenter,
    TopRight
}