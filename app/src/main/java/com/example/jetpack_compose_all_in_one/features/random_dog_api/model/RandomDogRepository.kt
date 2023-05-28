package com.example.jetpack_compose_all_in_one.features.random_dog_api.model

import com.example.jetpack_compose_all_in_one.di.RandomDogAPI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomDogRepository @Inject constructor(@RandomDogAPI private val apiService: RandomDogApiService) :
    IRandomDogRepository {

    override fun getRandomDogUsingRxJava() = apiService.getRandomDogUsingRxJava()

    override suspend fun getRandomDogUsingCoroutines() = apiService.getRandomDogUsingCoroutines()

    override suspend fun getRandomDogUsingFlows() = flow {
        emit(apiService.getRandomDogUsingFlow())
    }
}