package com.example.jetpack_compose_all_in_one.ui.views.chat

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatAppbar(title: String = "Sample", onBack: (() -> Unit)? = null) {
    TopAppBar(
        elevation = 4.dp,
        backgroundColor = Color(0xff00897B),
        contentColor = Color.White,
    )
    {

        Row(
            modifier = Modifier.weight(1f)
        ) {

            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .clickable {
                        onBack?.invoke()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )

                Surface(
                    modifier = Modifier.padding(6.dp),
                    shape = CircleShape,
                    color = Color.LightGray
                ) {
                    Icon(
                        imageVector = Icons.Filled.Groups,
                        contentDescription = null,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp)
                            .fillMaxHeight()
                            .aspectRatio(1f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { }
                    .padding(2.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        "This is dynamic chat sample",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        ChatAppbarActions()
    }
}

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = Devices.PIXEL_C)
@Composable
private fun ChatAppbarPreview() {
    ChatAppbar("Chat Bubble samples")
}

@Composable
private fun ChatAppbarActions(
    onCamClick: (() -> Unit)? = null,
    onCallClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IndicatingIconButton(
            onClick = { /* doSomething() */ },
            indication = ripple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.Videocam,
                contentDescription = null,
                tint = Color.White
            )
        }

        IndicatingIconButton(
            onClick = { /* doSomething() */ },
            indication = ripple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.Call,
                contentDescription = null,
                tint = Color.White
            )
        }

        IndicatingIconButton(
            onClick = { /* doSomething() */ },
            indication = ripple(bounded = false, radius = 22.dp),
            modifier = Modifier.then(Modifier.size(44.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}