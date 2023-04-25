package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.theme.ShadowColor


internal fun Modifier.materialShadow(
    bubbleState: BubbleState,
    path: Path,
    translateIfNeeded: Boolean = false
) = composed(
    inspectorInfo = {
        name = "shadow"
        value = bubbleState.shadow
    },
    factory = {
        bubbleState.shadow?.let { shadow: BubbleShadow ->

            val paint: Paint = remember(bubbleState) {
                Paint()
            }

            val frameworkPaint: NativePaint = remember(bubbleState) {
                paint.asFrameworkPaint()
            }

            drawBehind {
                if (translateIfNeeded) {
                    val left = if (bubbleState.isHorizontalLeftAligned())
                        -bubbleState.arrowWidth.toPx() else 0f
                    translate(left = left) {
                        drawShadow(bubbleState, frameworkPaint, path, paint)
                    }
                } else {
                    drawShadow(bubbleState, frameworkPaint, path, paint)
                }
            }
        } ?: this
    }
)

fun DrawScope.drawShadow(
    bubbleState: BubbleState,
    frameworkPaint: NativePaint,
    path: Path,
    paint: Paint
) {
    bubbleState.shadow?.let { shadow ->

        if (shadow.useSoftwareLayer) {
            this.drawIntoCanvas {

                val color = shadow.color

                val dx = shadow.offsetX.toPx() * 0.2f
                val dy = shadow.offsetY.toPx() * 0.7f
                val radius = shadow.shadowRadius.toPx()

                val shadowColor = color
                    .copy(alpha = shadow.alpha)
                    .toArgb()
                val transparent = color
                    .copy(alpha = 0f)
                    .toArgb()

                frameworkPaint.color = transparent

                frameworkPaint.setShadowLayer(
                    radius,
                    -dx,
                    dy,
                    shadowColor
                )
                it.drawPath(path, paint)
            }

        } else {
            val dx = shadow.offsetX.toPx()* 0.2f
            val dy = shadow.offsetY.toPx()* 0.7f
            translate(-dx, dy) {
                drawPath(color = shadow.color.copy(shadow.alpha), path = path)
            }
        }
    }
}


/**
 * Creates a shadow instance.
 *
 * @param color Color of the shadow
 * @param alpha of the color of the shadow
 * @param useSoftwareLayer use software layer to draw shadow with blur
 * @param dX x offset of shadow blur
 * @param dY y offset of shadow blur
 * @param shadowRadius radius of shadow blur if useSoftwareLayer is set to true
 */
fun BubbleShadow(
    color: Color = ShadowColor,
    alpha: Float = .7f,
    useSoftwareLayer: Boolean = true,
    dX: Dp = 1.dp,
    dY: Dp = 1.dp,
    shadowRadius: Dp = 1.dp,
): BubbleShadow {
    return BubbleShadow(
        color,
        alpha,
        dX,
        dY,
        shadowRadius,
        useSoftwareLayer
    )
}

/**
 * Creates a shadow instance.
 *
 * @param color Color of the shadow
 * @param alpha of the color of the shadow
 * @param useSoftwareLayer use software layer to draw shadow with blur
 * @param elevation elevation of the badge with shadow. Sets dx, dy,
 * and shadowRadius if software layer is used
 */
fun BubbleShadow(
    color: Color = ShadowColor,
    alpha: Float = .7f,
    useSoftwareLayer: Boolean = true,
    elevation: Dp = 1.dp
): BubbleShadow {
    return BubbleShadow(
        color,
        alpha,
        elevation,
        elevation,
        elevation,
        useSoftwareLayer
    )
}

class BubbleShadow internal constructor(
    val color: Color = ShadowColor,
    val alpha: Float = .7f,
    val shadowRadius: Dp = 1.dp,
    val offsetY: Dp = 1.dp,
    val offsetX: Dp = 1.dp,
    val useSoftwareLayer: Boolean = true
)