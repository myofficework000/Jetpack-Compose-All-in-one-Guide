package com.example.jetpack_compose_all_in_one.lessons.lesson_19

import retrofit2.http.GET

interface DogApiService {
    @GET("api/breeds/image/random")
    suspend fun getDogImage(): DogResponse
}