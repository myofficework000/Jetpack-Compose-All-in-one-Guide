package com.example.openai_app.model.remote.responsemodel

data class Choice(
    val finish_reason: String,
    val index: Int,
    val message: Message
)