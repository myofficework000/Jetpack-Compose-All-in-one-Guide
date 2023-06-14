package com.example.jetpack_compose_all_in_one.features.news_sample.data.remote

import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import com.example.jetpack_compose_all_in_one.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    /**
     *
    Retrieves the latest news articles.
    @return A Single object that emits the response containing the latest news articles.
     */
    @GET(Constants.LATEST_NEWS)
    fun getLatestNews(): Single<LatestNewsResponse>

    /**
     *
    Searches for news articles based on specified criteria.
    @param keywords The keywords to search for.
    @param start_date The start date of the news articles to search for.
    @param end_date The end date of the news articles to search for.
    @param category The category of the news articles to search for (optional).
    @param country The country of the news articles to search for (optional).
    @param language The language of the news articles to search for (optional).
    @return A Single object that emits the response containing the latest news articles.
     */
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