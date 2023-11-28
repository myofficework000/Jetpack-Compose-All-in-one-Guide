package com.example.jetpack_compose_all_in_one.demos.news_app.model.util.api

sealed class ApiResponse<out T> {
    data class OnSuccess<out T>(val data: T) : ApiResponse<T>()
    data class OnError<out T>(val error: String) : ApiResponse<T>()
    object Loading : ApiResponse<Nothing>()
}