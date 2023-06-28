package com.example.openai_app.model.remote.responsemodel

data class Usage(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)