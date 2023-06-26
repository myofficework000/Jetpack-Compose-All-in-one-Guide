package com.example.jetpack_compose_all_in_one.features.random_dog_api.model

import com.example.jetpack_compose_all_in_one.android_architectures.mvi.data.DogApiResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RandomDogApiService {

    @GET("random")
    suspend fun getRandomDogUsingCoroutines(): Response<RandomDogResponse>

    @GET("random")
    fun getRandomDogUsingRxJava(): Single<RandomDogResponse>

    @GET("random")
    suspend fun getRandomDogUsingFlow(): Response<RandomDogResponse>

}