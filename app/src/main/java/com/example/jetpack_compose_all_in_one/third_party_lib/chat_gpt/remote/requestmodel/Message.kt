package com.example.openai_app.model.remote.requestmodel

data class Message(
    val content: String,
    val role: String = "user"
)