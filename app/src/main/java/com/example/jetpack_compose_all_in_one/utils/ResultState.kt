package com.example.jetpack_compose_all_in_one.utils

sealed class ResultState<T : Any> {
    // Using X to show that the generic type is not linked to the sealed class
    class Loading<X : Any> : ResultState<X>()
    class Success<T : Any>(val body: T?) : ResultState<T>()
    class Error<T : Any>(val errorMessage: String) : ResultState<T>()
    class Exception<T : Any>(val exception: java.lang.Exception) : ResultState<T>()
}
