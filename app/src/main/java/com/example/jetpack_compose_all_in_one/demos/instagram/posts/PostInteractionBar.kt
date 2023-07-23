package com.example.jetpack_compose_all_in_one.demos.instagram.posts

import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PostInteractionBar(
    isLiked: Boolean,
    onLikeClicked: () -> Unit,
    onCommentsClicked: () -> Unit,
    onSendClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        IconToggleButton(checked = isLiked, onCheckedChange = { onLikeClicked() }) {
            val icon = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            val tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onPrimary
            Icon(icon, contentDescription = null, tint = tint)
        }
        IconToggleButton(checked = false, onCheckedChange = { onCommentsClicked() }) {
            Icon(Icons.Filled.Comment, contentDescription = null)
        }
        IconToggleButton(checked = false, onCheckedChange = { onSendClicked() }) {
            Icon(Icons.Filled.Send, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun PostInteractionBarPreview() {
    PostInteractionBar(
        isLiked = true,
        onLikeClicked = {},
        onCommentsClicked = {},
        onSendClicked = {}
    )
}