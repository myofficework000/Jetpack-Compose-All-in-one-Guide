package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
fun Modifier.swipeable(state: SwipeableState) = with(state) {

    val coroutineScope = rememberCoroutineScope()

    if (!isAnimationRunning) {

        LaunchedEffect(alpha) { animatedAlpha.snapTo(alpha) }

        LaunchedEffect(scale) { animatedScale.snapTo(scale) }

        LaunchedEffect(offset) { animatedShift.snapTo(shift) }

        LaunchedEffect(offset) { animatedRotation.snapTo(rotation) }

    }

    onGloballyPositioned {
        rect = it.boundsInParent()
    }
        .graphicsLayer {
            translationX = animatedShift.value
            rotationZ = animatedRotation.value
            transformOrigin = TransformOrigin(.5f, .75f)
        }
        .pointerInput(Unit) {
            if (!isAnimationRunning) {
                detectDragGestures(onDragStart = {
                    offset = Offset.Zero
                }, onDragCancel = {
                    offset = Offset.Zero
                }, onDragEnd = {
                    handleSwipeAction(coroutineScope, direction, offset)
                    offset = Offset.Zero
                }) { change, dragAmount ->
                    change.consumeAllChanges()
                    offset = offset?.plus(dragAmount)
                }
            }
        }
}