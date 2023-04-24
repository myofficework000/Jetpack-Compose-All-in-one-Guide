package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.ui.theme.dp_3

@Composable
fun SimpleTextButton(
    buttonMessage: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier,
        enabled = enabled,
        colors = buttonColors
    ) {
        Text(text = buttonMessage, textModifier)
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
fun OutlinedButton(
    buttonMessage: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(onClick = { onClick() }, modifier) {
        Text(text = buttonMessage, textModifier)
    }
}

@Composable
fun TextButton(buttonMessage: String) {
    TextButton(onClick = { }) {
        Text(text = buttonMessage)
    }
}

@Composable
fun SimpleIconButton(iconResourceInt: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(painterResource(id = iconResourceInt), "", modifier)
    }
}

@Composable
fun IconTextButton(
    iconResInt: Int?,
    text: String,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(onClick = { onClick() }, modifier) {
        Row() {
            iconResInt?.run{Icon(painterResource(this),"", iconModifier)}
            Text(text, textModifier)
        }
    }
}

@Composable
fun TextButtonWithIcon(
    iconResInt: Int?,
    text: String,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textColor: Color = LocalContentColor.current,
    iconColor: Color = textColor,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick() },
        modifier
    ) {
        Row {
            iconResInt?.run{
                Icon(painterResource(this),"", iconModifier, tint = iconColor)
            }
            Text(text, textModifier, color = textColor)
        }
    }
}

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontSize: TextUnit = 16.sp,
    isBold: Boolean = false,
    hasPadding: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick() },
        modifier = modifier.then(if (hasPadding) Modifier else Modifier.defaultMinSize(1.dp,1.dp)),
        enabled = enabled,
        contentPadding = if (hasPadding) ButtonDefaults.TextButtonContentPadding else PaddingValues(0.dp)
    ) {
        Text(
            text = text, modifier = textModifier, fontSize = fontSize,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun GradientButton(
    text: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    textColor: Color = LocalContentColor.current,
    textModifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(100),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.then(Modifier.background(gradient, shape)),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
    ) {
        Box(Modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                modifier = textModifier,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CheckboxText(
    text: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    checkboxModifier: Modifier = Modifier,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit)
) {
    Row {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = checkboxModifier,
            enabled = enabled
        )
        TextButton(
            text,
            textModifier = modifier,
            enabled = enabled
        ) { onCheckedChange(!checked) }
    }
}