package com.example.jetpack_compose_all_in_one.features.newsapi.repository

import com.example.jetpack_compose_all_in_one.di.NewsOrgAPI
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.features.newsapi.data.remote.NewsApiService
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.ResultState
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class NewsApiRepositoryImpl @Inject constructor(
    @NewsOrgAPI private val apiService: NewsApiService) : INewsApiRepository {

    override suspend fun getHeadLines(): Flow<ResultState<HeadlineResponse>> = flow {
        try {
            val response =
                apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY)
            if (response.isSuccessful) {
                emit(ResultState.Success(response.body()))
            } else {
                // Handle error case
                emit(ResultState.Error("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            // Handle exception
            emit(ResultState.Error("Error: ${e.message}"))
        }
    }
}