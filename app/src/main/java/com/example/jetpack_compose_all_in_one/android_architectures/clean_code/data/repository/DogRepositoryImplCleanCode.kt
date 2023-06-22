package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.repository

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.api.ApiDogCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogResponseCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.repositories.DogRepositoryCleanCode
import javax.inject.Inject

class DogRepositoryImplCleanCode @Inject constructor(
    private val apiDogCleanCode: ApiDogCleanCode
): DogRepositoryCleanCode {
    override suspend fun getRandomDog(): DogResponseCleanCode =
        apiDogCleanCode.getRandomDog()
}