package com.example.jetpack_compose_all_in_one.features.random_fox.model

import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface IRandomFoxRepository {
    suspend fun getRandomFoxCoroutines(): ResultState<RandomFoxResponse>
    suspend fun getRandomFoxFlow(): Flow<ResultState<RandomFoxResponse>>
    fun getRandomFoxRxJava(): Single<RandomFoxResponse>
}