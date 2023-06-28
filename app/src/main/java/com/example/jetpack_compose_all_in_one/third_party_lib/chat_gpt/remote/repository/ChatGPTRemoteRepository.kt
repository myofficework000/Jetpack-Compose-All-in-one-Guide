package com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.repository

import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.ChatGPTApiServices
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.NetworkResult
import com.example.openai_app.model.remote.requestmodel.ChatRequest
import com.example.openai_app.model.remote.requestmodel.Message
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ChatGPTRemoteRepository @Inject constructor(private val chatGPTApiServices: ChatGPTApiServices): IRepository {

    override suspend fun sendMessage(mess: String) = flow {
        try{
            val response = chatGPTApiServices
                .sendMessage(ChatRequest(listOf(Message(mess)))
            )
            with(response){
                if(this.isSuccessful) {
                    emit(NetworkResult.Success(this.body()))
                } else emit(NetworkResult.Error(this.code(),this.message()))
            }
        }catch (e: Exception){
            emit(NetworkResult.Exception(e))
        }
    }
}
