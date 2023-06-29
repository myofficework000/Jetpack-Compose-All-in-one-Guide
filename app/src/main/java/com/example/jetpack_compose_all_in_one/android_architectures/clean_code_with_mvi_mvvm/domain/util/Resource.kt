package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}