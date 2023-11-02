package com.example.jetpack_compose_all_in_one.lessons.lesson_8

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerAnimation(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val shimmerTranslateAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = modifier) {
        val brush = Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.5f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.5f),
                Color.LightGray.copy(alpha = 0.2f)
            ),
            start = Offset(-shimmerTranslateAnim, 0f),
            end = Offset(-shimmerTranslateAnim + size.width, 0f)
        )

        drawRect(brush = brush)
    }
}

@Composable
fun ShimmerCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = 4.dp
    ) {
        Box {
            // Content of the card
            // Replace this with your desired content
            Column(modifier = Modifier.padding(16.dp)) {
                ShimmerItem()
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerItem()
                Spacer(modifier = Modifier.height(8.dp))
                ShimmerItem()
            }

            // Shimmer effect
            ShimmerAnimation(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun ShimmerItem() {
    Box(modifier = Modifier.height(16.dp)) {
        ShimmerAnimation(modifier = Modifier.fillMaxSize())
    }
}

