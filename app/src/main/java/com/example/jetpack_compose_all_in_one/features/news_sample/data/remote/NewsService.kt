package com.example.jetpack_compose_all_in_one.features.news_sample.data.remote

import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import com.example.jetpack_compose_all_in_one.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface NewsService {

    @GET(Constants.LATEST_NEWS)
    fun getLatestNews() : Single<LatestNewsResponse>
}