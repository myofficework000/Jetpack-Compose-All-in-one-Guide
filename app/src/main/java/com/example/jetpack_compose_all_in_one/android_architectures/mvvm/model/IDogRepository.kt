package com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model

import com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model.data.DogResponse
import retrofit2.Response

interface IDogRepository {
    suspend fun getDogImage():Response<DogResponse>
}