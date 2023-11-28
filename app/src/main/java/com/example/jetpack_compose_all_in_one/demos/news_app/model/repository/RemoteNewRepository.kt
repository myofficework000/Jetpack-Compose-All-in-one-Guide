package com.example.jetpack_compose_all_in_one.demos.news_app.model.repository

import com.example.jetpack_compose_all_in_one.demos.news_app.model.models.RemoteNewsResponse
import retrofit2.Response

interface RemoteNewRepository {
    suspend fun getNews(): Response<RemoteNewsResponse>
}