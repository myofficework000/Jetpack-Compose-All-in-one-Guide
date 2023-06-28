package com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.NetworkResult
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.viewmodel.ChatGPTViewModel
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.openai_app.model.remote.responsemodel.ChatResponse

//Luan
@Composable
fun ChatUI(chatGPTViewModel: ChatGPTViewModel) {
    val result by chatGPTViewModel.result.observeAsState()
    var message by remember { mutableStateOf("") }

    LaunchedEffect(Unit){
        chatGPTViewModel.sendMessage("Hi")
    }

    when(result){
        is NetworkResult.Success -> {
            message = (result as NetworkResult.Success<ChatResponse>).data?.choices?.get(0)?.message?.content ?: ""
        }
        is NetworkResult.Error -> {
            message = (result as NetworkResult.Error<ChatResponse>).mess.toString()
        }
        is NetworkResult.Exception -> {
            message = (result as NetworkResult.Exception<ChatResponse>).e.message.toString()
        }
        else -> {
            message = stringResource(id = R.string.loading)
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (chatMess, chatInput) = createRefs()
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
                } )
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
            value = mess,
            placeholder = { Text(text = stringResource(id = R.string.ask_me)) },
            onValueChange = {newMess ->
                mess = newMess
            }
        )
        FloatingActionButton(
            modifier = Modifier.constrainAs(sendBtn)
            {
                start.linkTo(tField.end)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            },
            onClick = {
                sendMessage(mess)
                mess = ""
            }) {
            Text(text = stringResource(id = R.string.sendBtn))
        }
    }
}
@Composable
fun ChatMessages(modifier: Modifier, message: String) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = message,
        )
    }
}