package com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponseItem
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.remote.GitHubService

class GitHubPagingSource(private val apiService: GitHubService) : PagingSource<Int, DataResponseItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataResponseItem> {
        try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            // Make the API request to fetch repositories
            val repositories = apiService.getRepositories(pageSize, page)

            // Return the data and adjacent keys for Paging 3
            return LoadResult.Page(
                data = repositories,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (repositories.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            // Handle exceptions and return LoadResult.Error if there was an error fetching data
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataResponseItem>): Int? {
        return null
    }

}
