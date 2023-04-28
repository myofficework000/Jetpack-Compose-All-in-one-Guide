package com.example.jetpack_compose_all_in_one.ui.views.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.features.chatmodule.ChatHistoryItem
import com.example.jetpack_compose_all_in_one.ui.theme.DefaultBubbleColor
import com.example.jetpack_compose_all_in_one.ui.theme.SentMessageColor
import com.example.jetpack_compose_all_in_one.ui.views.chat.bubble.ArrowAlignment
import com.example.jetpack_compose_all_in_one.ui.views.chat.bubble.BubbleShadow
import com.example.jetpack_compose_all_in_one.ui.views.chat.bubble.rememberBubbleState
import com.example.jetpack_compose_all_in_one.utils.simpleFormat
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Locale

@Composable
fun DemoFullChat2(
    messages: List<ChatHistoryItem>,
    sendMessage: (ChatHistoryItem) -> Unit
) {
    val sdf = remember { SimpleDateFormat("hh:mm", Locale.ROOT) }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xffFBE9E7))
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            state = scrollState,
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
        ) {
            items(messages) { message ->

                // Remember random stats icon to not create at each recomposition
                val messageStatus = remember {
                    if (message.isRead) MessageStatus.READ else MessageStatus.RECEIVED
                }

                // Toggle between sent and received message
                /*if (!message.isCurrentUser) {
                    SentMessageRow(
                        drawArrow = true,
                        text = message.message,
                        messageTime = sdf.format(System.currentTimeMillis()),
                        messageStatus = messageStatus
                    )
                } else {
                    ReceivedMessageRow(
                        drawArrow = true,
                        text = message.message,
                        messageTime = sdf.format(System.currentTimeMillis()),
                    )
                }*/
                when (message.userId) {
                    "1" -> {
                        SentMessageRow(
                            drawArrow = true,
                            text = message.message,
                            messageTime = LocalDateTime
                                .ofInstant(Instant.ofEpochSecond(message.date), ZoneOffset.UTC)
                                .toLocalTime().simpleFormat(),
                            messageStatus = messageStatus
                        )
                    }

                    else -> {
                        ReceivedMessageRow(
                            drawArrow = true,
                            text = message.message,
                            messageTime = LocalDateTime
                                .ofInstant(Instant.ofEpochSecond(message.date), ZoneOffset.UTC)
                                .toLocalTime().simpleFormat()
                        )
                    }
                }
            }
        }

        ChatInput(
            onMessageChange = { messageContent ->
                sendMessage(
                    ChatHistoryItem(
                        userId = "1",
                        date = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                        message = messageContent,
                        isRead = false,
                        isCurrentUser = true
                    )
                )

                if (messages.isNotEmpty()) {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(messages.size - 1)
                    }
                }
            }
        )
    }
}

@Composable
private fun SentMessageRow(
    drawArrow: Boolean = true,
    text: String,
    messageTime: String,
    messageStatus: MessageStatus
) {
    Column(
        modifier = Modifier
            .padding(start = 60.dp, end = 8.dp, top = if (drawArrow) 2.dp else 0.dp, bottom = 2.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.End
    ) {

        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = SentMessageColor,
                alignment = ArrowAlignment.RightTop,
                cornerRadius = 8.dp,
                drawArrow = drawArrow,
                shadow = BubbleShadow(elevation = 1.dp),
                clickable = true
            )
        ) {
            ChatFlexBoxLayout(
                modifier = Modifier
                    .padding(start = 2.dp, top = 2.dp, end = 4.dp, bottom = 2.dp),
                text = text,
                messageStat = {
                    MessageTimeText(
                        modifier = Modifier.wrapContentSize(),
                        messageTime = messageTime,
                        messageStatus = messageStatus
                    )
                }
            )
        }
    }
}

@Composable
private fun ReceivedMessageRow(
    drawArrow: Boolean = true,
    text: String,
    messageTime: String
) {

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 60.dp, top = if (drawArrow) 2.dp else 0.dp, bottom = 2.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        BubbleLayout(
            bubbleState = rememberBubbleState(
                backgroundColor = DefaultBubbleColor,
                alignment = ArrowAlignment.LeftTop,
                drawArrow = drawArrow,
                cornerRadius = 8.dp,
                shadow = BubbleShadow(elevation = 1.dp),
                clickable = true
            )
        ) {
            ChatFlexBoxLayout(
                modifier = Modifier
                    .padding(start = 2.dp, top = 2.dp, end = 4.dp, bottom = 2.dp),
                text = text,
                messageStat = {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            modifier = Modifier.padding(top = 1.dp, bottom = 1.dp, end = 4.dp),
                            text = messageTime,
                            fontSize = 12.sp
                        )
                    }
                }
            )
        }
    }
}

// Release these after the old version is removed.
/*
enum class MessageStatus {
    PENDING, RECEIVED, READ
}

data class ChatMessage(
    val id: Long,
    var message: String,
    var date: Long
)*/
