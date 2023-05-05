package com.example.jetpack_compose_all_in_one.features.domain_search


import com.google.gson.annotations.SerializedName

data class DomainSearchResponse(
    @SerializedName("domains")
    val domains: List<DomainSearchItem>,
    @SerializedName("time")
    val time: String,
    @SerializedName("total")
    val total: Int
)