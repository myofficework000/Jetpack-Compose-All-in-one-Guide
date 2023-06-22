package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.repositories

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogResponseCleanCode

interface DogRepositoryCleanCode {
    suspend fun getRandomDog(): DogResponseCleanCode
}