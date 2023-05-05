package com.example.jetpack_compose_all_in_one.features.dog_api.model

import com.example.jetpack_compose_all_in_one.features.dog_api.model.data.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {

    @GET("woof.json")
    suspend fun getRandomDog():Response<DogResponse>
}