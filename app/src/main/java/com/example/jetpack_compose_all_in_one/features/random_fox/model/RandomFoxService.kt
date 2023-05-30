package com.example.jetpack_compose_all_in_one.features.random_fox.model

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RandomFoxService {
    @GET("floof")
    suspend fun getRandomFoxUsingCoroutines(): Response<RandomFoxResponse>

    @GET("floof")
    fun getRandomFoxUsingRxJava(): Single<RandomFoxResponse>
}