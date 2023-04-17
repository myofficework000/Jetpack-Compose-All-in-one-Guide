package com.example.jetpack_compose_all_in_one

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.ui.theme.dp_3

@Composable
fun SimpleButton(buttonMessage: String) {
    Button(onClick = { }) {
        Text(text = buttonMessage)
    }
}

@Composable
fun ButtonWithBorder(buttonMessage: String) {
    Button(
        onClick = { },
        border = BorderStroke(dp_3, Color.Black)
    ) {
        Text(text = buttonMessage)
    }
}

@Composable
fun ButtonWithRoundedCorners(buttonMessage: String) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = buttonMessage)
    }
}

@Composable
fun OutlinedButton(buttonMessage: String) {
    OutlinedButton(onClick = { }) {
        Text(text = buttonMessage)
    }
}

@Composable
fun TextButton(buttonMessage: String) {
    TextButton(onClick = { }) {
        Text(text = buttonMessage)
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontSize: TextUnit = 16.sp,
    isBold: Boolean = false
) {
    TextButton(onClick = { onClick() }, modifier = modifier, enabled = enabled) {
        Text(
            text = text, modifier = textModifier, fontSize = fontSize,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}