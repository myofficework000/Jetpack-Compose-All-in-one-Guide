package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.jetpack_compose_all_in_one.ui.theme.dp_1
import kotlin.random.Random

@Composable
fun CustomSpacer(height: Dp, color: Color = getRandomColor()) {
    Box(
        modifier = Modifier
            .padding(dp_1)
            .height(height)
            .fillMaxWidth()
            .background(color)
    )
}

@Composable
fun getRandomColor(): Color {
    val random = Random.Default
    return Color(random.nextFloat(), random.nextFloat(), random.nextFloat())
}
