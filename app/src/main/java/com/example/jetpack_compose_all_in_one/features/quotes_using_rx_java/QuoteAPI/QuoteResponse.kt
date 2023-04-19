package com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI

data class QuoteResponse(
    val _id: String,
    val author: String,
    val authorSlug: String,
    var content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)