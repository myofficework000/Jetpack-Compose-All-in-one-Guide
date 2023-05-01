package com.example.jetpack_compose_all_in_one.features.swipe_cards

import com.example.jetpack_compose_all_in_one.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiQuotes {
    @GET(Constants.QUOTES_ENDPOINT)
    suspend fun getQuotes(
        @Query("limit") limit: Int = 1
    ): Response<List<QuotesResponse>>
}