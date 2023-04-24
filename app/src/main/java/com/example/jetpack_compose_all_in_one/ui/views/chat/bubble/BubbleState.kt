package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.theme.DefaultBubbleColor


/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param backgroundColor color of Bubble
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.None] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param arrowRadius radius of the arrow curves the tip of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 * @param shadow of the arrow contains elevation, dx, dy, radius and color to draw shadow below bubble
 * @param padding padding between bubble and it's content. Set padding in bubbleState instead of using `Modifier.padding()`
 * to set padding.
 */
@Composable
fun rememberBubbleState(
    backgroundColor: Color = DefaultBubbleColor,
    cornerRadius: Dp = 8.dp,
    alignment: ArrowAlignment = ArrowAlignment.None,
    arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
    arrowOffsetX: Dp = 0.dp,
    arrowOffsetY: Dp = 0.dp,
    arrowWidth: Dp = 14.dp,
    arrowHeight: Dp = 14.dp,
    arrowRadius: Dp = 0.dp,
    drawArrow: Boolean = true,
    shadow: BubbleShadow? = null,
    padding: BubblePadding? = null,
    clickable: Boolean = false,
): BubbleState {

    return remember {
        BubbleState(
            backgroundColor = backgroundColor,
            cornerRadius = BubbleCornerRadius(
                topLeft = cornerRadius,
                topRight = cornerRadius,
                bottomLeft = cornerRadius,
                bottomRight = cornerRadius,
            ),
            alignment = alignment,
            arrowShape = arrowShape,
            arrowOffsetX = arrowOffsetX,
            arrowOffsetY = arrowOffsetY,
            arrowWidth = arrowWidth,
            arrowHeight = arrowHeight,
            arrowRadius = arrowRadius,
            drawArrow = drawArrow,
            shadow = shadow,
            padding = padding,
            clickable = clickable
        )
    }
}

/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param backgroundColor color of Bubble
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.None] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param arrowRadius radius of the arrow curves the tip of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 * @param shadow of the arrow contains elevation, dx, dy, radius and color to draw shadow below bubble
 * @param padding padding between bubble and it's content. Set padding in bubbleState instead of using `Modifier.padding()`
 * to set padding.
 */
@Composable
fun rememberBubbleState(
    backgroundColor: Color = DefaultBubbleColor,
    cornerRadius: BubbleCornerRadius = BubbleCornerRadius(
        topLeft = 8.dp,
        topRight = 8.dp,
        bottomLeft = 8.dp,
        bottomRight = 8.dp
    ),
    alignment: ArrowAlignment = ArrowAlignment.None,
    arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
    arrowOffsetX: Dp = 0.dp,
    arrowOffsetY: Dp = 0.dp,
    arrowWidth: Dp = 14.dp,
    arrowHeight: Dp = 14.dp,
    arrowRadius: Dp = 0.dp,
    drawArrow: Boolean = true,
    shadow: BubbleShadow? = null,
    padding: BubblePadding? = null,
    clickable:Boolean = false
): BubbleState {

    return remember {
        BubbleState(
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            alignment = alignment,
            arrowShape = arrowShape,
            arrowOffsetX = arrowOffsetX,
            arrowOffsetY = arrowOffsetY,
            arrowWidth = arrowWidth,
            arrowHeight = arrowHeight,
            arrowRadius = arrowRadius,
            drawArrow = drawArrow,
            shadow = shadow,
            padding = padding,
            clickable = clickable
        )
    }
}

/**
 *
 * [BubbleState] class  contains information about chat or speech **Bubble**.
 *
 * @param backgroundColor color of Bubble
 * @param cornerRadius Constructs a Radius for each side of bubble rectangle
 * @param alignment Arrow alignment determines in which side of the bubble this arrow should be drawn.
 * When [ArrowAlignment.None] is selected no arrow is drawn.
 * @param arrowShape Shape of the arrow, It can be right or isosceles triangle or curved shape
 * @param arrowOffsetX Vertical offset for arrow that is positioned on top or at the bottom of the bubble.
 * Positive values move arrow right while negative values move left. Arrow position
 * is limited between left of content and  content right minus arrow width.
 * @param arrowOffsetY Vertical offset for arrow that is positioned on left or right side of the bubble.
 * Positive values move arrow bottom while negative values move up. Arrow position
 * is limited between top of content and  content bottom minus arrow height.
 * @param arrowWidth width of the arrow
 * @param arrowHeight height of the arrow
 * @param arrowRadius radius of the arrow curves the tip of the arrow
 * @param drawArrow whether we should draw arrow or only have rectangle shape bubble
 * @param shadow of the arrow contains elevation, dx, dy, radius and color to draw shadow below bubble
 * @param padding padding between bubble and it's content. Set padding in bubbleState instead of using `Modifier.padding()`
 * to set padding.
 */
class BubbleState internal constructor(
    var backgroundColor: Color = DefaultBubbleColor,
    var cornerRadius: BubbleCornerRadius = BubbleCornerRadius(
        topLeft = 8.dp,
        topRight = 8.dp,
        bottomLeft = 8.dp,
        bottomRight = 8.dp,
    ),
    var alignment: ArrowAlignment = ArrowAlignment.None,
    var arrowShape: ArrowShape = ArrowShape.TRIANGLE_RIGHT,
    var arrowOffsetX: Dp = 0.dp,
    var arrowOffsetY: Dp = 0.dp,
    var arrowWidth: Dp = 14.dp,
    var arrowHeight: Dp = 14.dp,
    var arrowRadius: Dp = 0.dp,
    var drawArrow: Boolean = true,
    var shadow: BubbleShadow? = null,
    var padding: BubblePadding? = null,
    var clickable: Boolean = false
) {

    /**
     * Top position of arrow. This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */
    var arrowTop: Float = 0f
        internal set

    /**
     * Bottom position of arrow.  This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */

    var arrowBottom: Float = 0f
        internal set

    /**
     * Right position of arrow.  This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */
    var arrowLeft: Float = 0f
        internal set

    /**
     * Right position of arrow.  This is read-only for implementation. It's calculated when arrow
     * positions are calculated or adjusted based on width/height of bubble,
     * offsetX/y, arrow width/height.
     */
    var arrowRight: Float = 0f
        internal set


    /**
     * Arrow is on left side of the bubble
     */
    fun isHorizontalLeftAligned(): Boolean =
        (alignment == ArrowAlignment.LeftTop
                || alignment == ArrowAlignment.LeftBottom
                || alignment == ArrowAlignment.LeftCenter)


    /**
     * Arrow is on right side of the bubble
     */
    fun isHorizontalRightAligned(): Boolean =
        (alignment == ArrowAlignment.RightTop
                || alignment == ArrowAlignment.RightBottom
                || alignment == ArrowAlignment.RightCenter)


    /**
     * Arrow is on top left or right side of the bubble
     */
    fun isHorizontalTopAligned(): Boolean =
        (alignment == ArrowAlignment.LeftTop || alignment == ArrowAlignment.RightTop)


    /**
     * Arrow is on top left or right side of the bubble
     */
    fun isHorizontalBottomAligned(): Boolean =
        (alignment == ArrowAlignment.LeftBottom || alignment == ArrowAlignment.RightBottom)

    /**
     * Check if arrow is horizontally positioned either on left or right side
     */
    fun isArrowHorizontallyPositioned(): Boolean =
        isHorizontalLeftAligned()
                || isHorizontalRightAligned()


    /**
     * Arrow is at the bottom of the bubble
     */
    fun isVerticalBottomAligned(): Boolean =
        alignment == ArrowAlignment.BottomLeft ||
                alignment == ArrowAlignment.BottomRight ||
                alignment == ArrowAlignment.BottomCenter

    /**
     * Arrow is at the yop of the bubble
     */
    fun isVerticalTopAligned(): Boolean =
        alignment == ArrowAlignment.TopLeft ||
                alignment == ArrowAlignment.TopRight ||
                alignment == ArrowAlignment.TopCenter

    /**
     * Arrow is on left side of the bubble
     */
    fun isVerticalLeftAligned(): Boolean =
        (alignment == ArrowAlignment.BottomLeft) || (alignment == ArrowAlignment.TopLeft)


    /**
     * Arrow is on right side of the bubble
     */
    fun isVerticalRightAligned(): Boolean =
        (alignment == ArrowAlignment.BottomRight) || (alignment == ArrowAlignment.TopRight)


    /**
     * Check if arrow is vertically positioned either on top or at the bottom of bubble
     */
    fun isArrowVerticallyPositioned(): Boolean = isVerticalBottomAligned() || isVerticalTopAligned()
}
