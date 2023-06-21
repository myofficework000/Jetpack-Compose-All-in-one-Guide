package com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model

import com.example.jetpack_compose_all_in_one.di.DogAPI
import javax.inject.Inject

class DogRepository @Inject constructor(@DogAPI private val apiService: DogApiService):
    IDogRepository {
    override suspend fun getDogImage() = apiService.getRandomDog()
}