package com.example.openai_app.model.remote.requestmodel

data class ChatRequest(
    val messages: List<Message>,
    val model: String = "gpt-3.5-turbo"
)