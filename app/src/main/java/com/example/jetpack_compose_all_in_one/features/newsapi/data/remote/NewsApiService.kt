package com.example.jetpack_compose_all_in_one.features.newsapi.data.remote

import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET(Constants.HEADLINES_NEWS)
    suspend fun getTopHeadlines(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String
    ) : Response<HeadlineResponse>
}