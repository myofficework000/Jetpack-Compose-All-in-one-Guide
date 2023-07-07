package com.example.jetpack_compose_all_in_one.third_party_lib.paging3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpack_compose_all_in_one.di.GithubAPI
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.DataResponseItem
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
@HiltViewModel
class GitHubViewModel @Inject constructor(
    @GithubAPI private val repository: IRepository) : ViewModel() {

    private val _repositoryList = MutableStateFlow<PagingData<DataResponseItem>>(PagingData.empty())
    val repositoryData : StateFlow<PagingData<DataResponseItem>> = _repositoryList
    private var currentPage = 1

    fun fetchRepository(){
        viewModelScope.launch {
            repository.getRepository(currentPage).collectLatest { data ->
                _repositoryList.value = data
            }
        }
    }

    fun loadNextPage() {
       currentPage++
        fetchRepository()
    }

}*/

@HiltViewModel
class GitHubViewModel @Inject constructor(
    @GithubAPI private val repository: GitHubRepository
) : ViewModel() {
    val repositoryData: Flow<PagingData<DataResponseItem>> = repository.getRepositories().cachedIn(viewModelScope)

    private var currentPage = 1

    fun loadNextPage() {
        currentPage++
    }

}

