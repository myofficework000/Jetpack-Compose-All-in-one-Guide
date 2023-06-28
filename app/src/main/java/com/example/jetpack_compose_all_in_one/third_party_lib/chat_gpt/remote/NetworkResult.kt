package com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote

sealed class NetworkResult<T: Any>{
    data class Success<T: Any>(val data: T?): NetworkResult<T>()
    data class Error<T: Any>(val code: Int, val mess: String?): NetworkResult<T>()
    data class Exception<T: Any>(val e: Throwable): NetworkResult<T>()
    class Loading<T: Any>: NetworkResult<T>()
}
