package com.example.jetpack_compose_all_in_one.features.newsapi.data.data

data class HeadlineResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)