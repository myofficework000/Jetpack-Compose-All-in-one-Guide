package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble


import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import kotlin.math.min


/**
 * Function that returns bubble path.
 *
 * @param state sum of properties of this layout which includes arrow alignment, position,etc.
 * @param contentRect rectangle of content area
 *
 */
fun getBubbleClipPath(
    path: Path,
    state: BubbleState,
    contentRect: BubbleRect,
    density: Float,
) {

    path.reset()

    if (state.drawArrow) {
        if (state.isArrowHorizontallyPositioned()) {
            createHorizontalArrowPath(
                path = path,
                contentRect = contentRect,
                state = state,
                density = density
            )
        } else if (state.isArrowVerticallyPositioned()) {
            createVerticalArrowPath(
                path = path,
                contentRect = contentRect,
                state = state,
                density = density
            )
        }
    }

    getRoundedRectPath(state, path, contentRect, density)
}

private fun getRoundedRectPath(
    state: BubbleState,
    path: Path,
    contentRect: BubbleRect,
    density: Float
) {

    val alignment = state.alignment

    val cornerRadius: BubbleCornerRadius = state.cornerRadius

    val maxRadius = contentRect.height / 2f

    val drawArrow = state.drawArrow

    var topLeftCornerRadius = cornerRadius.topLeft.value * density
        .coerceAtMost(maxRadius)
    var topRightCornerRadius = cornerRadius.topRight.value * density
        .coerceAtMost(maxRadius)
    var bottomLeftCornerRadius = cornerRadius.bottomLeft.value * density
        .coerceAtMost(maxRadius)
    var bottomRightCornerRadius = cornerRadius.bottomRight.value * density
        .coerceAtMost(maxRadius)

    val arrowTop = state.arrowTop
    val arrowBottom = state.arrowBottom
    val arrowLeft = state.arrowLeft
    val arrowRight = state.arrowRight

    if (drawArrow) {
        when (alignment) {
            // Arrow on left side of the bubble
            ArrowAlignment.LeftTop, ArrowAlignment.LeftCenter, ArrowAlignment.LeftBottom -> {
                topLeftCornerRadius = min(arrowTop, topLeftCornerRadius)
                bottomLeftCornerRadius = min(bottomLeftCornerRadius, (contentRect.height - arrowBottom))
            }

            // Arrow on right side of the bubble
            ArrowAlignment.RightTop, ArrowAlignment.RightCenter, ArrowAlignment.RightBottom -> {
                topRightCornerRadius = min(arrowTop, topRightCornerRadius)
                bottomRightCornerRadius = min(bottomRightCornerRadius, (contentRect.height - arrowBottom))
            }

            // Arrow at the bottom of bubble
            ArrowAlignment.BottomLeft, ArrowAlignment.BottomCenter, ArrowAlignment.BottomRight -> {

                bottomLeftCornerRadius = min(arrowLeft, bottomLeftCornerRadius)
                bottomRightCornerRadius = min(bottomRightCornerRadius, (contentRect.width - arrowRight))
            }

            // Arrow at the top of bubble
            ArrowAlignment.TopLeft, ArrowAlignment.TopCenter, ArrowAlignment.TopRight -> {
                topLeftCornerRadius = min(arrowLeft, topLeftCornerRadius)
                topRightCornerRadius = min(topRightCornerRadius, (contentRect.width - arrowRight))
            }else -> Unit
        }
    }

    path.addRoundRect(
        RoundRect(
            rect = Rect(contentRect.left, contentRect.top, contentRect.right, contentRect.bottom),
            topLeft = CornerRadius(
                topLeftCornerRadius,
                topLeftCornerRadius
            ),
            topRight = CornerRadius(
                topRightCornerRadius,
                topRightCornerRadius
            ),
            bottomRight = CornerRadius(
                bottomRightCornerRadius,
                bottomRightCornerRadius
            ),
            bottomLeft = CornerRadius(
                bottomLeftCornerRadius,
                bottomLeftCornerRadius
            )
        )
    )
}