package com.example.jetpack_compose_all_in_one.features.domain_search


import com.google.gson.annotations.SerializedName

data class DomainSearchItem(
    @SerializedName("country")
    val country: String?,
    @SerializedName("create_date")
    val createDate: String,
    @SerializedName("domain")
    val domain: String,
    @SerializedName("isDead")
    val isDead: String,
    @SerializedName("update_date")
    val updateDate: String
)