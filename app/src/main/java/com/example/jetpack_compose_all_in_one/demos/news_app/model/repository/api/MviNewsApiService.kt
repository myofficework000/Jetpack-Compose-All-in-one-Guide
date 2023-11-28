package com.example.jetpack_compose_all_in_one.demos.news_app.model.repository.api

import com.example.jetpack_compose_all_in_one.demos.news_app.model.models.RemoteNewsResponse
import com.example.jetpack_compose_all_in_one.demos.news_app.model.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface MviNewsApiService {

    @GET(Constants.ENDPOINT)
    suspend fun getNews(): Response<RemoteNewsResponse>

}