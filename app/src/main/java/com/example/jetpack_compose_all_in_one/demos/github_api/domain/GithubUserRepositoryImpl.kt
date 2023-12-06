package com.example.jetpack_compose_all_in_one.demos.github_api.domain

import com.example.jetpack_compose_all_in_one.demos.github_api.data.model.GithubUser
import com.example.jetpack_compose_all_in_one.demos.github_api.data.remote.GithubUserApi
import com.example.jetpack_compose_all_in_one.demos.github_api.data.repository.GithubUserRepository
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(private val githubUserApi: GithubUserApi): GithubUserRepository {
    override suspend fun getGithubUser(since: Int): List<GithubUser> {
        return githubUserApi.getGithubUser(since)
    }
}