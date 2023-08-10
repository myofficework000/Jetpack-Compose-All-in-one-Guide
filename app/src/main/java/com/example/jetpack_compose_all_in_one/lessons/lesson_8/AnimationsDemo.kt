package com.example.jetpack_compose_all_in_one.lessons.lesson_8

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.theme.LightBlueToBlue30
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
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
        targetValue = if (animate) 100 else 0,
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

@Composable
fun FloatAnimationExample() {
    var isVisible by remember { mutableStateOf(false) }

    val animateValue by animateFloatAsState(
        targetValue = if (isVisible) 0f else 1f,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer {
                    translationX = animateValue * 100 * if (isVisible) -1 else 1
                    alpha = 1 - animateValue
                }
                .background(LightBlueToBlue30)
        )
        SimpleTextButton(if (isVisible) "Float out" else "Float in") {
            isVisible = !isVisible
        }
    }
}

@Composable
fun ShootArrow(modifier: Modifier, yOffset: Int, item: @Composable () -> Unit) {

    Box(modifier) {
        Box(
            Modifier
                .offset(y = yOffset.dp)
                .align(Alignment.TopCenter)
        ) {
            item()
        }
    }
}

@Composable
fun Arrow() {
    Image(
        painter = painterResource(id = R.drawable.baseline_arrow_downward_24),
        contentDescription = null,
        modifier = Modifier
            .padding(top = dp_15)
            .size(dp_50),
    )
}

@Composable
fun DownArrowAnimation() {
    val yState by remember { mutableStateOf(0) }
    val yOffset = animateIntAsState(
        targetValue = yState,
        animationSpec = tween(durationMillis = 4000, easing = LinearEasing)
    )

    ShootArrow(modifier = Modifier.fillMaxSize(fraction = .8f), yOffset = yOffset.value) {
        Arrow()
    }
}

@Composable
fun SpinLottieAnimation(){
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_lottie))
    val progress by animateLottieCompositionAsState(composition = lottieComposition, iterations = LottieConstants.IterateForever)

    LottieAnimation(composition = lottieComposition, progress = { progress })
}
