package com.example.jetpack_compose_all_in_one.ui.views.chatmodule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.chatmodule.ChatHistoryItem
import com.example.jetpack_compose_all_in_one.ui.components.GradientTextField
import com.example.jetpack_compose_all_in_one.ui.components.SimpleIconButton
import com.example.jetpack_compose_all_in_one.ui.theme.Blue10ToBlue20
import com.example.jetpack_compose_all_in_one.ui.theme.LightBlueToBlue30

@Composable
fun ChatModule(
    chatHistory: MutableList<ChatHistoryItem>,
    onSubmit: (String) -> Unit,
) {
    val chatMessageState = rememberSaveable { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(LightBlueToBlue30)) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {
            items(chatHistory) {
                ChatBox(data = it)
            }
        }
        ChatSender(chatMessageState) {
            chatMessageState.value = ""
            onSubmit(it)
        }
    }
}

@Composable
private fun ChatSender(
    state: MutableState<String>,
    onSubmit: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        GradientTextField(
            value = state.value,
            gradient = Blue10ToBlue20,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Enter message", color = Color.Gray) }
        ) { state.value = it }
        SimpleIconButton(
            iconResourceInt = R.drawable.baseline_chat_24,
            Modifier.padding(start = 4.dp)
        ) { onSubmit(state.value) }
    }
}

@Composable
private fun ChatBox(
    data: ChatHistoryItem
) {
    Box(
        Modifier.fillMaxWidth()
    ) {
        if (data.userId == "1") {
            Text(data.message, Modifier.align(Alignment.CenterEnd))
        } else {
            Text(data.message, Modifier.align(Alignment.CenterStart))
        }
    }
}


