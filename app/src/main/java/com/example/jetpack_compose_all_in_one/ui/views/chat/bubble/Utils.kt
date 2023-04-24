package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.ui.graphics.Color

/**
 * Retrieve rectangle for measuring for space to be used content other than arrow itself.
 *
 * @param bubbleState state that contains bubble properties
 * @param rectContent we store position and dimensions for space required for
 * drawing rounded rectangle without arrow
 * @param width is the total width reserved for content and arrow if available in horizontal position
 * @param height is the total height reserved for content and arrow if available in vertical position
 */
internal fun setContentRect(
    bubbleState: BubbleState,
    rectContent: BubbleRect,
    width: Int,
    height: Int,
    density: Float
) {

    val isHorizontalRightAligned = bubbleState.isHorizontalRightAligned()
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalBottomAligned = bubbleState.isVerticalBottomAligned()

    val arrowWidth = bubbleState.arrowWidth.value * density
    val arrowHeight = bubbleState.arrowHeight.value * density

    when {
        isHorizontalLeftAligned -> {
            rectContent.set(
                left = arrowWidth,
                top = 0f,
                right = width.toFloat(),
                bottom = height.toFloat()
            )

        }

        isHorizontalRightAligned -> {
            rectContent.set(
                0f,
                0f,
                width.toFloat() - arrowWidth,
                height.toFloat()
            )

        }

        isVerticalBottomAligned -> {
            rectContent.set(
                0f,
                0f,
                width.toFloat(),
                height.toFloat() - arrowHeight
            )
        }

        else -> {
            rectContent.set(
                0f,
                0f,
                width.toFloat(),
                height.toFloat()
            )
        }
    }
}

fun Color.darkenColor(factor: Float): Color {

    val colorFactor = factor.coerceAtMost(1f)
    return copy(
        alpha,
        red * colorFactor,
        green * colorFactor,
        blue * colorFactor
    )
}