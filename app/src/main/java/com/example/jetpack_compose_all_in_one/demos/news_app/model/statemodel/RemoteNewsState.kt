package com.example.jetpack_compose_all_in_one.demos.news_app.model.statemodel

import com.example.jetpack_compose_all_in_one.demos.news_app.model.models.Article

sealed class RemoteNewsState {
    object Idle : RemoteNewsState()
    data class Loading(val isLoading: Boolean) : RemoteNewsState()
    data class NewsList(val list: List<Article>) : RemoteNewsState()
    data class Error(val error: String) : RemoteNewsState()

}