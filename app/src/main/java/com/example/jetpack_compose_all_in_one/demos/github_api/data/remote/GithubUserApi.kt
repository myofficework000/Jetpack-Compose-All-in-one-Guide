package com.example.jetpack_compose_all_in_one.demos.github_api.data.remote

import com.example.jetpack_compose_all_in_one.demos.github_api.data.model.GithubUserList
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUserApi {
    @GET("users")
    suspend fun getGithubUser(@Query("since") since: Int): GithubUserList
}