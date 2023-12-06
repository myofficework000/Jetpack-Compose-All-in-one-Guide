package com.example.jetpack_compose_all_in_one.demos.github_api.domain

import com.example.jetpack_compose_all_in_one.demos.github_api.data.model.GithubUser
import com.example.jetpack_compose_all_in_one.demos.github_api.data.repository.GithubUserRepository
import javax.inject.Inject

class GetGithubUserUseCase @Inject constructor(private val githubUserRepository: GithubUserRepository) {

    suspend operator fun invoke(since: Int): List<GithubUser>{
        return githubUserRepository.getGithubUser(since)
    }
}