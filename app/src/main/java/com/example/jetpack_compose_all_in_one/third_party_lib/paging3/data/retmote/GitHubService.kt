package com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.retmote

import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponse
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("repositories")
    suspend fun getRepositories(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): List<DataResponseItem>
}
