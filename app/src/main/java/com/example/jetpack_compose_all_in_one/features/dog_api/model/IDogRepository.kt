package com.example.jetpack_compose_all_in_one.features.dog_api.model

import com.example.jetpack_compose_all_in_one.features.dog_api.model.data.DogResponse
import retrofit2.Response

interface IDogRepository {
    suspend fun getDogImage():Response<DogResponse>
}