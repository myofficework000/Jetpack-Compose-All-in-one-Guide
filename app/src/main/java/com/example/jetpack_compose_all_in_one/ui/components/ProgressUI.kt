package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.ui.theme.Green100
import com.example.jetpack_compose_all_in_one.ui.theme.Green700
import com.example.jetpack_compose_all_in_one.ui.theme.Pink80
import com.example.jetpack_compose_all_in_one.ui.theme.Purple40
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_100
import com.example.jetpack_compose_all_in_one.ui.theme.dp_16
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8

@Composable
fun SimpleProgress() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CircularProgressIndicator(
            modifier = Modifier.padding(dp_16),
            color = Purple40,
            strokeWidth = dp_10
        )
    }
}

@Composable
fun CustomProgress() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CircularProgressIndicator(
            modifier = Modifier.size(dp_100),
            color = Pink80,
            strokeWidth = dp_10
        )
    }
}

@Composable
fun LinearProgress(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        LinearProgressIndicator()
    }
}

@Composable
fun CustomLinearProgress(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = Green700,
    backgroundColor: Color = Green100,
    clipShape: Shape = RoundedCornerShape(dp_16)
    )
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(dp_8)
        ) {
            Box(modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    SimpleProgress()
}