package com.example.jetpack_compose_all_in_one.android_architectures.mvi.repo

import com.example.jetpack_compose_all_in_one.android_architectures.mvi.data.DogApiResponse
import com.example.jetpack_compose_all_in_one.di.RandomDogAPI
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogApiService
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogResponse
import retrofit2.Response
import javax.inject.Inject

class DogAPIRepo @Inject constructor(@RandomDogAPI private val api: RandomDogApiService) {

    suspend fun getDogInfo(): Response<RandomDogResponse> {
        return api.getRandomDogUsingCoroutines()
    }
}