package com.example.jetpack_compose_all_in_one.features.domain_search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDomainSearch {
    @GET("search")
    suspend fun searchDomain(
        @Query("domain") domain: String
    ): Response<DomainSearchResponse>
}