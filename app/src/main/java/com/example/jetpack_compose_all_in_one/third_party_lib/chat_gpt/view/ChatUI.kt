package com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.NetworkResult
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.viewmodel.ChatGPTViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.Blue30
import com.example.jetpack_compose_all_in_one.ui.theme.GrayBackground
import com.example.jetpack_compose_all_in_one.ui.theme.GrayBackground2
import com.example.jetpack_compose_all_in_one.ui.theme.Green200
import com.example.jetpack_compose_all_in_one.ui.theme.ShadowColor
import com.example.jetpack_compose_all_in_one.ui.theme.White
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.jetpack_compose_all_in_one.ui.views.chat.textBackgroundColor
import com.example.openai_app.model.remote.responsemodel.ChatResponse

@Preview
@Composable
private fun ChatUIPrev() {
    ChatUI(chatGPTViewModel = viewModel())
}

@Composable
fun ChatUI(chatGPTViewModel: ChatGPTViewModel) {
    val result by chatGPTViewModel.result.observeAsState()
    var message by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false)}

    LaunchedEffect(Unit){
        chatGPTViewModel.sendMessage("Hi")
    }

    when(result){
        is NetworkResult.Success -> {
            message = (result as NetworkResult.Success<ChatResponse>).data?.choices?.get(0)?.message?.content ?: ""
            loading = false
        }
        is NetworkResult.Error -> {
            message = (result as NetworkResult.Error<ChatResponse>).mess.toString()
            loading = false
        }
        is NetworkResult.Exception -> {
            message = (result as NetworkResult.Exception<ChatResponse>).e.message.toString()
            loading = false
        }
        else -> { loading = true }
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(dp_10),
        shape = RoundedCornerShape(dp_10),
        border = BorderStroke(dp_5, Green200),
        color = GrayBackground2
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (chatMess, chatInput) = createRefs()
            if(loading){
                val strokeWidth = dp_5
                val progress = createRef()
                CircularProgressIndicator(
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                Blue30,
                                radius = size.width / 2 - strokeWidth.toPx() / 2,
                                style = Stroke(strokeWidth.toPx())
                            )
                        }
                        .constrainAs(progress) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    color = ShadowColor,
                    strokeWidth = strokeWidth
                )
            }
            ChatMessages(
                modifier = Modifier.constrainAs(chatMess){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                message = message
            )
            ChatInput(
                modifier = Modifier
                    .constrainAs(chatInput) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(),
                sendMessage = { mess ->
                    chatGPTViewModel.sendMessage(mess)
                    loading = true
                    message = ""
                }
            )
        }
    }
}
@Composable
fun ChatInput(modifier: Modifier, sendMessage:(String) -> Unit) {
    var mess by remember { mutableStateOf("") }
    ConstraintLayout(
        modifier = modifier
            .padding(dp_10)
    ) {
        val (tField, sendBtn) = createRefs()

        TextField(
            modifier = Modifier.constrainAs(tField)
            {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(sendBtn.start)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = GrayBackground,
                unfocusedContainerColor = GrayBackground
            ),
            value = mess,
            placeholder = { Text(text = stringResource(id = R.string.ask_me), color = White) },
            onValueChange = {newMess ->
                mess = newMess
            },
        )
        FloatingActionButton(
            modifier = Modifier.constrainAs(sendBtn)
            {
                start.linkTo(tField.end)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            containerColor = GrayBackground,
            onClick = {
                sendMessage(mess)
                mess = ""
            }
        ) {
            Text(
                text = stringResource(id = R.string.sendBtn),
                color = White
            )
        }
    }
}
@Composable
fun ChatMessages(modifier: Modifier, message: String) {
    Column(
        modifier = modifier.padding(dp_10)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = message,
            color = White
        )
    }
}