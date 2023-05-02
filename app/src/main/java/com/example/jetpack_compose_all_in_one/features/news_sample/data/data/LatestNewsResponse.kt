package com.example.jetpack_compose_all_in_one.features.news_sample.data.data

data class LatestNewsResponse(
    val news: List<News>,
    val page: Int,
    val status: String
)