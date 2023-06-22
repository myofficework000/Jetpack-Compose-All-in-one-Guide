package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.api

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogResponseCleanCode
import retrofit2.http.GET

interface ApiDogCleanCode {
    @GET("random")
    suspend fun getRandomDog(): DogResponseCleanCode
}