package com.example.jetpack_compose_all_in_one.demos.thread

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.ui.theme.Purple40

@Composable
@Preview(showBackground = true)
fun ThreadCommentSection() {
    Column(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(200.dp))
        Icon(
            Icons.Rounded.Message,
            contentDescription = "",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterHorizontally)
                .testTag("comment_icon"),
            tint = Purple40
        )
        Text(
            text = "Be the first to like & comment",
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .testTag("first_comment_static_text"),
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Start a Conversation",
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(20.dp)
                .testTag("start_conversation_static_text"),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium),
            textAlign = TextAlign.Center
        )
    }
}