package com.example.jetpack_compose_all_in_one.demos.news_app.model.repository

import com.example.jetpack_compose_all_in_one.demos.news_app.model.models.RemoteNewsResponse
import com.example.jetpack_compose_all_in_one.demos.news_app.model.repository.api.MviNewsApiService
import com.example.jetpack_compose_all_in_one.di.MviNewsAPI
import retrofit2.Response
import javax.inject.Inject


class RemoteNewsRepositoryImpl @Inject constructor(@MviNewsAPI private val newsApiService: MviNewsApiService) :
    RemoteNewRepository {
    override suspend fun getNews(): Response<RemoteNewsResponse> = newsApiService.getNews()
}