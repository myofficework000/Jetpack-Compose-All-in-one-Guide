package com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.repository

import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.NetworkResult
import com.example.openai_app.model.remote.responsemodel.ChatResponse
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun sendMessage(mess: String): Flow<NetworkResult<ChatResponse>>
}