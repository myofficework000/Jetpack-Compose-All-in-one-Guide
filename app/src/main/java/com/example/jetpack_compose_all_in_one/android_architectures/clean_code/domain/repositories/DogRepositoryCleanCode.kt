package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.repositories

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogEntityCleanCode

interface DogRepositoryCleanCode {
    suspend fun getRandomDog(): DogEntityCleanCode
}