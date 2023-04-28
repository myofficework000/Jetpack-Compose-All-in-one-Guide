package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun rememberSwipeableState(
    swipeThreshold: Float = .4f,
    swipeAngle: Float = 25f,
    onLeft: () -> Unit,
    onRight: () -> Unit,
) = remember { SwipeableState(swipeThreshold, swipeAngle, onLeft, onRight) }

class SwipeableState constructor(
    private val swipeThreshold: Float = .4f,
    private val swipeAngle: Float = 25f,
    private val onLeft: () -> Unit,
    private val onRight: () -> Unit,
) {

    internal var rect: Rect by mutableStateOf(Rect.Zero)

    internal var offset: Offset? by mutableStateOf(null)

    internal val alpha: Float by derivedStateOf {
        lerp(abs(offsetPercentage), 0f, swipeThreshold, 0f, 1f).coerceIn(0f, 1f)
    }

    internal val shift: Float by derivedStateOf {
        offset?.run { x.coerceIn(-rect.width, rect.width) } ?: 0f
    }

    internal val scale: Float by derivedStateOf {
        lerp(abs(offsetPercentage), 0f, swipeThreshold, .9f, 1f).coerceIn(.9f, 1f)
    }

    internal val rotation: Float by derivedStateOf {
        offset?.run { (offsetPercentage * swipeAngle).coerceIn(-swipeAngle, swipeAngle) } ?: 0f
    }

    internal val direction by derivedStateOf {
        offset?.run {
            when {
                x < 0 -> SwipeDirection.LEFT
                x > 0 -> SwipeDirection.RIGHT
                else -> SwipeDirection.NONE
            }
        } ?: SwipeDirection.NONE
    }

    internal var isAnimationRunning by mutableStateOf(false)

    internal var isSwipingToLeft by mutableStateOf(false)

    internal var isSwipingToRight by mutableStateOf(false)

    internal fun handleSwipeAction(
        coroutineScope: CoroutineScope,
        direction: SwipeDirection,
        swipeOffset: Offset?,
    ) {
        coroutineScope.launch {

            val swiped = swipeOffset?.run { swipeAvailable(x) } ?: true

            isSwipingToDefault = !swiped
            isSwipingToLeft = swiped && direction == SwipeDirection.LEFT
            isSwipingToRight = swiped && direction == SwipeDirection.RIGHT

            isAnimationRunning = true

            joinAll(
                launch {
                    animatedAlpha.animateTo(if (isSwipingToDefault) 1f else 0f, animation)
                },
                launch {
                    animatedScale.animateTo(if (isSwipingToDefault) .9f else 1f, animation)
                },
                launch {
                    animatedShift.animateTo(
                        if (swiped) when (direction) {
                            SwipeDirection.LEFT -> rect.width * -1.5f
                            SwipeDirection.RIGHT -> rect.width * 1.5f
                            else -> 0f
                        } else 0f, animation
                    )
                },
                launch {
                    animatedRotation.animateTo(
                        if (swiped) when (direction) {
                            SwipeDirection.LEFT -> swipeAngle * -1.5f
                            SwipeDirection.RIGHT -> swipeAngle * 1.5f
                            else -> 0f
                        } else 0f, animation
                    )
                }
            )

            isAnimationRunning = false

            if (swiped) when (direction) {
                SwipeDirection.LEFT -> onLeft()
                SwipeDirection.RIGHT -> onRight()
                else -> Unit
            }

            animatedAlpha.snapTo(1f)
            animatedScale.snapTo(.9f)
            animatedShift.snapTo(0f)
            animatedRotation.snapTo(0f)

            isSwipingToDefault = false
            isSwipingToLeft = false
            isSwipingToRight = false
        }
    }

    internal fun handleControlsAction(coroutineScope: CoroutineScope, direction: SwipeDirection) =
        handleSwipeAction(coroutineScope, direction, null)

    internal val animatedAlpha = Animatable(0f)

    internal val animatedShift = Animatable(0f)

    internal val animatedScale = Animatable(0f)

    internal val animatedRotation = Animatable(0f)

    private val animationDuration = 250

    private val animation = tween<Float>(durationMillis = animationDuration, easing = LinearEasing)

    private var isSwipingToDefault by mutableStateOf(false)

    private val offsetPercentage by derivedStateOf { offset?.run { x / rect.width } ?: 0f }

    private fun swipeAvailable(value: Float) = abs(value) / rect.width > swipeThreshold

    private fun lerp(
        value: Float,
        minValue: Float,
        maxValue: Float,
        targetMinValue: Float,
        targetMaxValue: Float,
    ) = (value - minValue) / (maxValue - minValue) * (targetMaxValue - targetMinValue) + targetMinValue
}