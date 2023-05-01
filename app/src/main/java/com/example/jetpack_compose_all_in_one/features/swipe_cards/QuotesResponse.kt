package com.example.jetpack_compose_all_in_one.features.swipe_cards


import com.google.gson.annotations.SerializedName

data class QuotesResponse(
    @SerializedName("author")
    val author: String,
    @SerializedName("authorSlug")
    val authorSlug: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("dateAdded")
    val dateAdded: String,
    @SerializedName("dateModified")
    val dateModified: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("length")
    val length: Int,
    @SerializedName("tags")
    val tags: List<String>
)