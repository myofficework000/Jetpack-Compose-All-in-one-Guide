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
fun SimpleButton() {
    Button(onClick = { }) {
        Text(text = "Simple Button")
    }
}

@Composable
fun ButtonWithBorder() {
    Button(
        onClick = { },
        border = BorderStroke(dp_3, Color.Black)
    ) {
        Text(text = "Simple button with border")
    }
}

@Composable
fun ButtonWithRoundedCorners() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = "Button with rounded corners")
    }
}

@Composable
fun OutlinedButton() {
    OutlinedButton(onClick = { }) {
        Text(text = "Outlined Button")
    }
}

@Composable
fun TextButton() {
    TextButton(onClick = { }) {
        Text(text = "Text Button")
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
        Text(text = text, modifier = textModifier, fontSize = fontSize,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal)
    }
}