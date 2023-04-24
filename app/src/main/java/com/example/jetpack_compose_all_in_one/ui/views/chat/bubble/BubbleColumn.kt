package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.*
import kotlin.math.roundToInt

/**
 * Bubble layout that acts as a [Column]
 * @param modifier belong to whole layout. After this modifier [drawBubble] functions is called
 *  then [Modifier.then] called to chain [bubbleModifier].
 * @param bubbleModifier this [Modifier] is used after drawing bubble
 * @param bubbleState contains information about chat or speech **Bubble**.
 * @param content combination of composables. Children composables are laid out as [Column] does.
 */
@Composable
fun BubbleColumn(
    modifier: Modifier = Modifier,
    bubbleModifier: Modifier = Modifier,
    bubbleState: BubbleState,
    content: @Composable () -> Unit
) {
    val contentRect = remember { BubbleRect() }
    val path = remember { Path() }
    var pressed by remember { mutableStateOf(false) }

    val newModifier = modifier
        .materialShadow(bubbleState, path)
        .drawBehind {
//            println("📝️ BubbleColumn() DRAWING align:${bubbleState.alignment}, size: $size, path: $path, rectContent: $contentRect")
            drawPath(
                path = path,
                color = if (pressed) bubbleState.backgroundColor.darkenColor(.9f)
                else bubbleState.backgroundColor,
            )
        }
        .then(
            if (bubbleState.clickable) {
                modifier.pointerInput(Unit) {
                    forEachGesture {
                        awaitPointerEventScope {
                            val down: PointerInputChange = awaitFirstDown()
                            pressed = down.pressed
                            waitForUpOrCancellation()
                            pressed = false
                        }
                    }
                }
            } else modifier
        )
        .then(bubbleModifier)

    Layout(
        content = content,
        modifier = newModifier

    ) { measurables, constraints ->

//        println("BubbleColumn() LAYOUT align:${bubbleState.alignment}, rect: $rect")
        measureBubbleColumnResult(
            bubbleState = bubbleState,
            measurables = measurables,
            constraints = constraints,
            rectContent = contentRect,
            path = path
        )
    }
}

private fun MeasureScope.measureBubbleColumnResult(
    bubbleState: BubbleState,
    measurables: List<Measurable>,
    constraints: Constraints,
    rectContent: BubbleRect,
    path: Path
): MeasureResult {

    val arrowWidth = (bubbleState.arrowWidth.value * density).roundToInt()
    val arrowHeight = (bubbleState.arrowHeight.value * density).roundToInt()

    val paddingStart = ((bubbleState.padding?.start ?: 0.dp).value * density).roundToInt()
    val paddingTop = ((bubbleState.padding?.top ?: 0.dp).value * density).roundToInt()
    val paddingEnd = ((bubbleState.padding?.end ?: 0.dp).value * density).roundToInt()
    val paddingBottom = ((bubbleState.padding?.bottom ?: 0.dp).value * density).roundToInt()

    // Check arrow position
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalTopAligned = bubbleState.isVerticalTopAligned()
    val isHorizontallyPositioned = bubbleState.isArrowHorizontallyPositioned()
    val isVerticallyPositioned = bubbleState.isArrowVerticallyPositioned()

    // Offset to limit max width when arrow is horizontally placed
    // if we don't remove arrowWidth bubble will overflow from it's parent as much as arrow
    // width is. So we measure our placeable as content + arrow width + horizontal padding
    val offsetX: Int = (paddingStart + paddingEnd) + if (isHorizontallyPositioned) {
        arrowWidth
    } else 0

    // Offset to limit max height when arrow is vertically placed
    val offsetY: Int = (paddingTop + paddingBottom) + if (isVerticallyPositioned) {
        arrowHeight
    } else 0

    val placeables = measurables.map { measurable: Measurable ->
        // 🔥 With constraints.offset we limit placeable width/height to maxWidth/Height - offsetX/Y
        // Even without arrow it's required to limit width/height for placeable to take space
        // when padding is applied
        measurable.measure(constraints.offset(-offsetX, -offsetY))
    }

    val desiredWidth: Int =
        constraints.constrainWidth(placeables.maxOf { it.width } + offsetX)
    val desiredHeight: Int =
        constraints.constrainHeight(placeables.sumOf { it.height } + offsetY)

    println(
        "🚛 measureBubbleColumnResult() align:${bubbleState.alignment}, " +
                "placeableWidth: ${placeables.maxOf { it.width }}, " +
                "desiredWidth: $desiredWidth, limitedWidth: ${
                    constraints.constrainWidth(
                        placeables.maxOf { it.width })
                }\n" +
                "constraints: $constraints"
    )

    setContentRect(
        bubbleState,
        rectContent,
        desiredWidth,
        desiredHeight,
        density = density
    )

    getBubbleClipPath(
        path = path,
        state = bubbleState,
        contentRect = rectContent,
        density = density
    )

    val xPos = if (isHorizontalLeftAligned) paddingStart + arrowWidth else paddingStart
    val yPos = if (isVerticalTopAligned) paddingTop + arrowHeight else paddingTop

    return layout(desiredWidth, desiredHeight) {

        var yPosNext = 0
        placeables.forEach { placeable: Placeable ->
//            println(
//                "🎃 measureBubbleColumnResult() LAYOUT align: ${bubbleState.alignment}\n" +
//                        "xPos: $xPos, yPos: $yPos, yPosNext: yPosNext, " +
//                        "placeable width: ${placeable.width}, " +
//                        "height: ${placeable.height}, " +
//                        "rect: $rectContent"
//            )

            placeable.place(xPos, yPos + yPosNext)
            yPosNext += placeable.height
        }
    }
}