package com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpack_compose_all_in_one.di.GithubAPI
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponseItem
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.remote.GitHubService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
class RepositoryImpl @Inject constructor(
    @GithubAPI private val apiService : GitHubService) : IRepository {

    override suspend fun getRepository(page :Int): Flow<PagingData<DataResponseItem>> {
        return Pager(PagingConfig(page)){
            GitHubPagingSource(apiService)
        }.flow
    }
}*/
class GitHubRepositoryImpl @Inject constructor(
    @GithubAPI private val apiService: GitHubService) : GitHubRepository {
    override fun getRepositories(): Flow<PagingData<DataResponseItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 5),
            pagingSourceFactory = { GitHubPagingSource(apiService) }
        ).flow
    }
}

