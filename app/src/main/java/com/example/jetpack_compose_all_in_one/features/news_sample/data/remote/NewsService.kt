package com.example.jetpack_compose_all_in_one.features.news_sample.data.remote

import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import com.example.jetpack_compose_all_in_one.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET(Constants.LATEST_NEWS)
    fun getLatestNews() : Single<LatestNewsResponse>

    @GET(Constants.SEARCH_NEWS)
    fun searchNews(
        @Query("keywords") keywords: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("category") category: String? = null,
        @Query("country") country: String? = null,
        @Query("language") language: String? = null
    ): Single<LatestNewsResponse>
}