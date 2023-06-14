package com.example.jetpack_compose_all_in_one.features.news_sample.data.data

/**
 *
Represents the response object for the latest news articles.
@property news The list of news articles.
@property page The current page number.
@property status The status of the response.
 */
data class LatestNewsResponse(
    val news: List<News>,
    val page: Int,
    val status: String
)