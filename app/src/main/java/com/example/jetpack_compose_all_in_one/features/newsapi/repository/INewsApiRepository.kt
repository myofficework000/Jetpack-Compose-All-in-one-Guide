package com.example.jetpack_compose_all_in_one.features.newsapi.repository

import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface INewsApiRepository {

    suspend fun getHeadLines() : Flow<ResultState<HeadlineResponse>>
}