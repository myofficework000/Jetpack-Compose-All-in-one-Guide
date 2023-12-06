package com.example.jetpack_compose_all_in_one.demos.github_api.data.repository

import com.example.jetpack_compose_all_in_one.demos.github_api.data.model.GithubUser

interface GithubUserRepository {
    suspend fun getGithubUser(since: Int): List<GithubUser>
}