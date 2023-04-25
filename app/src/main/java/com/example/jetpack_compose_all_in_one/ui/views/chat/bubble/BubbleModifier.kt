package com.example.jetpack_compose_all_in_one.ui.views.chat.bubble

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import kotlin.math.roundToInt

/**
 * Measures content of composable it's used with and adds bubble layout after measuring content
 * and adding arrow based on [BubbleState.alignment]. If none is selected no space is reserved
 * for arrow. If [BubbleState.drawArrow] is false space is reserved but arrow is not drawn.
 * This is useful for creating secondary messages like chat apps which only first or last
 * message have arrow.
 *
 * ### * Shadow
 * To draw shadow native canvas and shadow layer is used if software layer is not supported
 * or not working properly either set [BubbleShadow.useSoftwareLayer] false or use
 * [drawBubbleWithShape] which draws default shadow of Android api.
 *
 * With [BubbleShadow] it's possible to create colored shadows unlike default shadows
 *
 * ### * Padding
 * If you want to set padding for element in **bubble** use [BubbleState.padding] property.
 *
 * ### * Usage
 * This modifier is suitable for one composable. If you need to add multiple elements
 * inside bubble use a wrapper like [Column].
 */
fun Modifier.drawBubble(bubbleState: BubbleState) = composed(

    // pass inspector information for debug
    inspectorInfo = debugInspectorInfo {
        // name should match the name of the modifier
        name = "drawBubble"
        // add name and value of each argument
        properties["bubbleState"] = bubbleState
    },

    factory = {

        val rectContent = remember { BubbleRect() }
        val path = remember { Path() }
        var pressed by remember { mutableStateOf(false) }

        Modifier
            .layout { measurable, constraints ->
//                println("Modifier.drawBubble() LAYOUT align:${bubbleState.alignment}")
                measureBubbleResult(bubbleState, measurable, constraints, rectContent, path)
            }

            .materialShadow(bubbleState, path, true)
            .drawBehind {
//                println(
//                    "✏️ Modifier.drawBubble() DRAWING align:${bubbleState.alignment}," +
//                            " size: $size, path: $path, rectContent: $rectContent"
//                )
                val left = if (bubbleState.isHorizontalLeftAligned())
                    -bubbleState.arrowWidth.toPx() else 0f

                translate(left = left) {
                    drawPath(
                        path = path,
                        color = if (pressed) bubbleState.backgroundColor.darkenColor(.9f)
                        else bubbleState.backgroundColor,
                    )

                }
            }
            .then(
                if (bubbleState.clickable) {
                    this.pointerInput(Unit) {
                        forEachGesture {
                            awaitPointerEventScope {
                                val down: PointerInputChange = awaitFirstDown()
                                pressed = down.pressed
                                waitForUpOrCancellation()
                                pressed = false
                            }
                        }
                    }
                } else this
            )
            .then(
                bubbleState.padding?.let { padding ->
                    this.padding(
                        padding.start,
                        padding.top,
                        padding.end,
                        padding.bottom
                    )
                } ?: this
            )
    }
)

/**
 * Measures content of composable it's used with and adds bubble layout after measuring content
 * and adding arrow based on [BubbleState.alignment]. If none is selected no space is reserved
 * for arrow. If [BubbleState.drawArrow] is false space is reserved but arrow is not drawn.
 * This is useful for creating secondary messages like chat apps which only first or last
 * message have arrow.
 *
 * ## Note
 * This overloaded function does 2 layout passes to set [GenericShape] for shadow and background.
 *
 * ### * Shadow
 * Uses [GenericShape] to create a shape for background and shadow.
 *
 * With [BubbleShadow] it's possible to create colored shadows unlike default shadows
 *
 * ### * Padding
 * If you want to set padding for element in **bubble** use [BubbleState.padding] property.
 *
 * ### * Usage
 * This modifier is suitable for one composable. If you need to add multiple elements
 * inside bubble use a wrapper like [Column].
 */
fun Modifier.drawBubbleWithShape(bubbleState: BubbleState) = composed(

    // pass inspector information for debug
    inspectorInfo = debugInspectorInfo {
        // name should match the name of the modifier
        name = "drawBubble"
        // add name and value of each argument
        properties["bubbleState"] = bubbleState
    },

    factory = {

        val rectContent = remember { BubbleRect() }
        val path = remember { Path() }
        var shapeUpdated by remember { mutableStateOf(false) }

        var pressed by remember { mutableStateOf(false) }

        var shape by remember {
            mutableStateOf(
                GenericShape { size: Size, layoutDirection: LayoutDirection ->

                }
            )
        }

        Modifier
            // Measure layout and set content rectangle and arrow if available
            .layout { measurable, constraints ->
//                println(
//                    "🍏 drawBubbleWithShape() LAYOUT  align:${bubbleState.alignment}, " +
//                            "shape: $shape, path: $path, rect: $rectContent"
//                )
                val result =
                    measureBubbleResult(bubbleState, measurable, constraints, rectContent, path)
                if (!shapeUpdated) {
                    shape = GenericShape { size: Size, layoutDirection: LayoutDirection ->

                        val left = if (bubbleState.isHorizontalLeftAligned())
                            -bubbleState.arrowWidth.toPx() else 0f
                        addPath(path, Offset(left, 0f))
                    }
                    shapeUpdated = true
                }

                result
            }
            // Draw shadow
            .then(
                if (bubbleState.shadow != null) {
                    this.shadow(bubbleState.shadow?.offsetX ?: 1.dp, shape)
                } else this
            )
            .background(
                if (pressed) bubbleState.backgroundColor.darkenColor(.9f)
                else bubbleState.backgroundColor,
                shape
            )
            .then(
                if (bubbleState.clickable) {
                    this.pointerInput(Unit) {
                        forEachGesture {
                            awaitPointerEventScope {
                                val down: PointerInputChange = awaitFirstDown()
                                pressed = down.pressed
                                waitForUpOrCancellation()
                                pressed = false
                            }
                        }
                    }
                } else this
            )

            // Add padding
            .then(
                bubbleState.padding?.let { padding ->
                    this.padding(
                        padding.start,
                        padding.top,
                        padding.end,
                        padding.bottom
                    )
                } ?: this
            )
    }
)

/**
 * Measure layout to create a bubble with rounded rectangle with arrow is [bubbleState]
 * has parameter to draw arrow.
 */
internal fun MeasureScope.measureBubbleResult(
    bubbleState: BubbleState,
    measurable: Measurable,
    constraints: Constraints,
    rectContent: BubbleRect,
    path: Path
): MeasureResult {

    val arrowWidth = (bubbleState.arrowWidth.value * density).roundToInt()
    val arrowHeight = (bubbleState.arrowHeight.value * density).roundToInt()

    // Check arrow position
    val isHorizontalLeftAligned = bubbleState.isHorizontalLeftAligned()
    val isVerticalTopAligned = bubbleState.isVerticalTopAligned()
    val isHorizontallyPositioned = bubbleState.isArrowHorizontallyPositioned()
    val isVerticallyPositioned = bubbleState.isArrowVerticallyPositioned()

    // Offset to limit max width when arrow is horizontally placed
    // if we don't remove arrowWidth bubble will overflow from it's parent as much as arrow
    // width is. So we measure our placeable as content + arrow width
    val offsetX: Int = if (isHorizontallyPositioned) {
        arrowWidth
    } else 0

    // Offset to limit max height when arrow is vertically placed

    val offsetY: Int = if (isVerticallyPositioned) {
        arrowHeight
    } else 0

    val placeable = measurable.measure(constraints.offset(-offsetX, -offsetY))

    val desiredWidth = constraints.constrainWidth(placeable.width + offsetX)
    val desiredHeight: Int = constraints.constrainHeight(placeable.height + offsetY)

    println(
        "🚌 measureBubbleResult() align:${bubbleState.alignment}, arrowWidth: $arrowWidth, " +
                "placeableWidth: ${placeable.width}, desiredWidth: $desiredWidth\n" +
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


    // Position of content(Text or Column/Row/Box for instance) in Bubble
    // These positions effect placeable area for our content
    // if xPos is greater than 0 it's required to translate background path(bubble) to match total
    // area since left of  xPos is not usable(reserved for arrowWidth) otherwise
    val xPos = if (isHorizontalLeftAligned) arrowWidth else 0
    val yPos = if (isVerticalTopAligned) arrowHeight else 0


    return layout(desiredWidth, desiredHeight) {
//        println(
//            "🤡 measureBubbleResult() LAYOUT align: ${bubbleState.alignment}\n" +
//                    "x: $xPos, y: $yPos, " +
//                    "placeable width: ${placeable.width}, " +
//                    "height: ${placeable.height}, " +
//                    "rect: $rectContent"
//        )

        placeable.place(xPos, yPos)
    }
}