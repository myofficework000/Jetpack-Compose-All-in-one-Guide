package com.example.jetpack_compose_all_in_one.lessons.lesson_8

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PositionAnimationExample() {
    var animate by remember { mutableStateOf(false) }

    val screenWidth = 300.dp

    val offset by animateFloatAsState(
        targetValue = if (animate) 0f else screenWidth.value,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .offset(x = offset.dp)
            .background(Color.Blue)
            .clickable { animate = !animate }
    )
}

@Composable
fun ScalingAnimationExample() {
    var animate by remember { mutableStateOf(false) }

    val originalSize: Dp = 100.dp
    val expandedSize: Dp = 200.dp

    val scale = animateFloatAsState(
        targetValue = if (animate) 1.5f else 1f,
        animationSpec = tween(durationMillis = 500)
    ).value

    Box(
        modifier = Modifier
            .size(originalSize)
            .scale(scale)
            .background(Color.Green)
            .clickable { animate = !animate }
    )
}

@Composable
fun FadeAnimationExample() {
    var animate by remember { mutableStateOf(false) }

    val opacity by animateFloatAsState(
        targetValue = if (animate) 0.1f else 1f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Red.copy(alpha = opacity))
            .clickable { animate = !animate }
    )
}

@Composable
fun RotationAnimationWithDelayExample() {
    var animate by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (animate) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )
    )

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .size(100.dp)
            .rotate(rotation)
            .background(Color.Red)
            .clickable {
                animate = !animate
                if (animate) {
                    // Delay the animation reversal by 2 seconds
                    coroutineScope.launch {
                        delay(1000)
                        animate = false
                    }
                }
            }
    )
}

@Composable
fun RoundAnimationExample() {
    var animate by remember { mutableStateOf(false) }


    val border by animateIntAsState(
        targetValue = if(animate) 100 else 0,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(border))
            .background(Color.Blue)
            .clickable { animate = !animate }
    )



}