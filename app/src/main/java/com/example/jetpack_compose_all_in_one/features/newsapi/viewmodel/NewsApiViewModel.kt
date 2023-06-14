package com.example.jetpack_compose_all_in_one.features.newsapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.di.NewsOrgAPI
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.features.newsapi.repository.INewsApiRepository
import com.example.jetpack_compose_all_in_one.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsApiViewModel @Inject constructor(
    @NewsOrgAPI private val newsApiRepository: INewsApiRepository) : ViewModel() {

    private val _news = MutableStateFlow<ResultState<HeadlineResponse>?>(null)
    val news : StateFlow<ResultState<HeadlineResponse>?> = _news

    fun getHeadlinesNews(){
        viewModelScope.launch {
            newsApiRepository.getHeadLines().collect(){ response ->
                _news.value = response
            }
        }
    }
}