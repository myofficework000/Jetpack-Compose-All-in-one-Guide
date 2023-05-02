package com.example.jetpack_compose_all_in_one.features.news_sample.data.data

data class News(
    val author: String,
    val category: List<String>,
    val description: String,
    val id: String,
    val image: String,
    val language: String,
    val published: String,
    val title: String,
    val url: String
)