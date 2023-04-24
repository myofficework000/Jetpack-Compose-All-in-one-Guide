package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.ui.graphics.Path

/**
 * Create path for arrow either aligned left or right side of the bubble
 */
fun createHorizontalArrowPath(
    path: Path,
    contentRect: BubbleRect,
    state: BubbleState,
    density: Float,
) {
    val alignment = state.alignment
    if (alignment == ArrowAlignment.None) return

    val contentHeight: Float = contentRect.height
    val contentLeft: Float = contentRect.left
    val contentRight: Float = contentRect.right
    val contentTop: Float = contentRect.top

    val arrowWidth: Float = state.arrowWidth.value * density
    val arrowHeight: Float = (state.arrowHeight.value * density).coerceAtMost(contentHeight)

    // This is offset from top/bottom or center for arrows on left or right.
    // Maximum offset + arrow height cannot be bigger
    // than bottom of bubble or smaller than top of bubble.
    val arrowTop = calculateArrowTopPosition(
        state,
        arrowHeight,
        contentTop,
        contentHeight,
        density
    )
    state.arrowTop = arrowTop
    val arrowBottom = arrowTop + arrowHeight
    state.arrowBottom = arrowBottom

    val arrowShape = state.arrowShape

    when (alignment) {

        ArrowAlignment.LeftTop -> {
            // move to top of arrow at the top of left corner
            path.moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowTop)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(0f, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.LeftBottom -> {

            // move to top of arrow at the bottom left corner
            path.moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowBottom)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.LeftCenter -> {

            // move to top of arrow at the top of left corner
            path.moveTo(contentLeft, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(0f, arrowTop)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(0f, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentLeft, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.RightTop -> {

            // move to top right corner of the content
            path.moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.RightBottom -> {

            // move to bottom right corner of the content
            path.moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(contentRight + arrowWidth, arrowBottom)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.RightCenter -> {

            // move to top right corner of the content
            path.moveTo(contentRight, arrowTop)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(contentRight + arrowWidth, arrowTop + arrowHeight / 2f)
                    path.lineTo(contentRight, arrowBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        else -> Unit
    }
//    path.close()
}


/**
 * Calculate top position of the arrow on either left or right side
 */
private fun calculateArrowTopPosition(
    state: BubbleState,
    arrowHeight: Float,
    contentTop: Float,
    contentHeight: Float,
    density: Float,
): Float {

    val arrowOffsetY = state.arrowOffsetY.value * density

    var arrowTop = when {
        state.isHorizontalTopAligned() -> {
            contentTop + arrowOffsetY
        }
        state.isHorizontalBottomAligned() -> {
            contentHeight + arrowOffsetY - arrowHeight
        }
        else -> {
            (contentHeight - arrowHeight) / 2f + arrowOffsetY
        }
    }

    if (arrowTop < 0) arrowTop = 0f

    if (arrowTop + arrowHeight > contentHeight) arrowTop = contentHeight - arrowHeight

    return arrowTop
}

/**
 * Create path for arrow that is at the bottom of the bubble
 */
fun createVerticalArrowPath(
    path: Path,
    contentRect: BubbleRect,
    state: BubbleState,
    density: Float
) {

    val alignment = state.alignment

    if (alignment == ArrowAlignment.None) return

    val contentHeight = contentRect.height
    val contentWidth = contentRect.width

    val contentLeft = contentRect.left
    val contentRight = contentRect.right
    val contentTop = contentRect.top
    val contentBottom = contentRect.bottom

    val cornerRadius = state.cornerRadius

    // TODO This is for bottom arrow, we take only bottom corners to have space to draw arrow
//    val radiusSumOnArrowSide: Float =
//        (cornerRadius.bottomLeft + cornerRadius.bottomRight).value * density
//
//    // Width of the arrow is limited to height of the bubble minus sum of corner radius
//    // of top and bottom in respective side
//
//    val arrowWidthInPx = state.arrowWidth.value * density
//
//    val arrowWidth =
//        if (arrowWidthInPx + radiusSumOnArrowSide > contentWidth)
//            contentWidth - radiusSumOnArrowSide else arrowWidthInPx

    val arrowWidth = (state.arrowWidth.value * density).coerceAtMost(contentRect.width)
    val arrowHeight = state.arrowHeight.value * density

    val arrowLeft = calculateArrowLeftPosition(
        state,
        arrowWidth,
        contentLeft,
        contentWidth,
        density
    )

    val arrowRight = arrowLeft + arrowWidth
    val arrowBottom = contentBottom + arrowHeight

    state.arrowLeft = arrowLeft
    state.arrowRight = arrowRight

    val arrowShape = state.arrowShape

    when (alignment) {
        ArrowAlignment.BottomLeft -> {
            path.moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(arrowLeft, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.CURVED -> {

                }
            }

        }

        ArrowAlignment.BottomRight -> {
            path.moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    path.lineTo(arrowRight, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        ArrowAlignment.BottomCenter -> {
            path.moveTo(arrowLeft, contentBottom)

            when (arrowShape) {

                ArrowShape.TRIANGLE_RIGHT -> {
                    // Draw horizontal line to left
                    path.lineTo(arrowLeft, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.TRIANGLE_ISOSCELES -> {
                    path.lineTo(arrowLeft + arrowWidth / 2f, arrowBottom)
                    path.lineTo(arrowRight, contentBottom)
                }

                ArrowShape.CURVED -> {

                }
            }
        }

        else -> Unit
    }
//    path.close()
}

private fun calculateArrowLeftPosition(
    state: BubbleState,
    arrowWidth: Float,
    contentLeft: Float,
    contentWidth: Float,
    density: Float
): Float {

    val arrowOffsetX: Float = state.arrowOffsetX.value * density

    var arrowLeft = when {
        state.isVerticalLeftAligned() -> {
            contentLeft + arrowOffsetX
        }
        state.isVerticalRightAligned() -> {
            contentWidth + arrowOffsetX - arrowWidth
        }
        else -> {
            (contentWidth - arrowWidth) / 2f + arrowOffsetX
        }
    }

    if (arrowLeft < 0) arrowLeft = 0f

    if (arrowLeft + arrowWidth > contentWidth) arrowLeft = contentWidth - arrowWidth

    return arrowLeft
}