package com.example.jetpack_compose_all_in_one.features.domain_search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DomainSearchViewModel @Inject constructor(
    private val domainSearchRepository: IDomainSearchRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    val searchResult = mutableStateOf<ResultState<DomainSearchResponse>>(ResultState.Loading())
    val searchBox = mutableStateOf("")

    fun searchDomain(name: String) {
        viewModelScope.launch(ioDispatcher) {
            domainSearchRepository.searchDomain(name).apply {
                searchResult.value = this
            }
        }
    }
}