package com.example.jetpack_compose_all_in_one.data.QuoteAPI

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