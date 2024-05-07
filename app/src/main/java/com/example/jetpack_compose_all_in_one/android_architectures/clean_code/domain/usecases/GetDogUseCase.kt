package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.usecases

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.repositories.DogRepositoryCleanCode
import javax.inject.Inject

class GetDogUseCase @Inject constructor(
    private val repo: DogRepositoryCleanCode
) {
    suspend operator fun invoke() = repo.getRandomDog()
}