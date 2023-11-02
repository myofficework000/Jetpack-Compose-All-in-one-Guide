package com.example.jetpack_compose_all_in_one.demos.history_of_day.repository

import com.example.jetpack_compose_all_in_one.demos.history_of_day.model.Dates
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.ApiService
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.NetworkResult
import javax.inject.Inject


class HistoryRepositoryImpl @Inject
constructor(private val apiService: ApiService) : HistoryRepository {

  override suspend fun fetchHistory():
      NetworkResult<List<Dates>> {
    var res = apiService.getHistoryOfToday()
    return  if (res.isNullOrEmpty()) {
       NetworkResult.Error("Something went wrong")
    } else {
       NetworkResult.Success("Success",res)
    }
  }
}