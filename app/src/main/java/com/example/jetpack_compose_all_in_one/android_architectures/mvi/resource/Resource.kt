package com.example.jetpack_compose_all_in_one.android_architectures.mvi.resource

sealed class Resource<T>(open val data: T) {
    data class Initial<T>(override val data: T) : Resource<T>(data)
    data class Success<T>(override val data: T) : Resource<T>(data)
    data class Failure<T>(override val data: T) : Resource<T>(data)
    data class Loading<T>(override val data: T) : Resource<T>(data)
}