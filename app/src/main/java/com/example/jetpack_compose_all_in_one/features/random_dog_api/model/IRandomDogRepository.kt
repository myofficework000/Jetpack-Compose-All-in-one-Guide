package com.example.jetpack_compose_all_in_one.features.random_dog_api.model

import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRandomDogRepository {
    suspend fun getRandomDogUsingCoroutines(): Response<RandomDogResponse>

    fun getRandomDogUsingRxJava(): Single<RandomDogResponse>

    suspend fun getRandomDogUsingFlows(): Flow<Response<RandomDogResponse>>

}