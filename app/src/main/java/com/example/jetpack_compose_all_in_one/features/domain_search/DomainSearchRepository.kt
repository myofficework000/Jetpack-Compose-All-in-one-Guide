package com.example.jetpack_compose_all_in_one.features.domain_search

import com.example.jetpack_compose_all_in_one.utils.ResultState
import retrofit2.Response
import javax.inject.Inject

class DomainSearchRepository @Inject constructor(
    private val apiDomainSearch: ApiDomainSearch
): IDomainSearchRepository {
    override suspend fun searchDomain(name: String) = apiDomainSearch.searchDomain(name).run {
        if (body() != null) ResultState.Success(body())
        else ResultState.Error(errorBody()?.string() ?: "Unknown Error")
    }
}

interface IDomainSearchRepository {
    suspend fun searchDomain(name: String): ResultState<DomainSearchResponse>
}