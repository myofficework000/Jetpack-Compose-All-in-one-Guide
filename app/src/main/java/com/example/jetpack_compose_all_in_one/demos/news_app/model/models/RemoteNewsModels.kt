package com.example.jetpack_compose_all_in_one.demos.news_app.model.models

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)


data class Source(
    val id: String,
    val name: String
)


data class RemoteNewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)