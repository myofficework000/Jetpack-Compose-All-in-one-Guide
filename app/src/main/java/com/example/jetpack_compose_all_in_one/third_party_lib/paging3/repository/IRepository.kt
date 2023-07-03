package com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository

import androidx.paging.PagingData
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponse
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponseItem
import kotlinx.coroutines.flow.Flow

/*interface IRepository {
    suspend fun getRepository(page :Int) : Flow<PagingData<DataResponseItem>>
}*/
interface GitHubRepository {
    fun getRepositories(): Flow<PagingData<DataResponseItem>>
}
