package com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi


import com.google.gson.annotations.SerializedName

data class TmdbResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TmdbResponseItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    companion object {
        val empty = TmdbResponse(
            page = 1,
            results = listOf(),
            totalPages = 1,
            totalResults = 0
        )
    }
}