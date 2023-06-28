package com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote

import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.utils.Constants.CHAT_ENDPOINT
import com.example.openai_app.model.remote.requestmodel.ChatRequest
import com.example.openai_app.model.remote.responsemodel.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGPTApiServices {
    @POST(CHAT_ENDPOINT)
    suspend fun sendMessage(@Body chatRequest: ChatRequest): Response<ChatResponse>
}