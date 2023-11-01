package com.example.jetpack_compose_all_in_one.demos.history_of_day.service

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {

  class Success<T>(message: String?,data: T) : NetworkResult<T>(data,message)
  class Error<T>(message: String?) : NetworkResult<T>(null,message)

}