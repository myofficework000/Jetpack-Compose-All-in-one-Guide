package com.example.jetpack_compose_all_in_one.features.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Stable
interface TagColors {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun contentColor(enabled: Boolean): State<Color>
}

@Immutable
private class DefaultTagColors(
    private val backgroundColor: Color,
    private val contentColor: Color
) : TagColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(newValue = backgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(newValue = contentColor)
    }
}

object TagDefaults {
    @Composable
    fun tagColors(
        backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = .2f),
        contentColor: Color = MaterialTheme.colorScheme.primary
    ): TagColors = DefaultTagColors(backgroundColor = backgroundColor, contentColor = contentColor)
}

@Composable
fun InterestTag(
    text: String,
    modifier: Modifier = Modifier,
    colors: TagColors = TagDefaults.tagColors(),
    shape: Shape = RoundedCornerShape(4.dp),
    style: TextStyle = typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
    onClick: () -> Unit = {}
) {
    val tagModifier = modifier
        .padding(4.dp)
        .clickable(onClick = onClick)
        .clip(shape = shape)
        .background(colors.backgroundColor(enabled = true).value)
        .padding(horizontal = 8.dp, vertical = 4.dp)
    Text(
        text = text,
        color = colors.contentColor(enabled = true).value,
        modifier = tagModifier,
        style = style
    )
}
