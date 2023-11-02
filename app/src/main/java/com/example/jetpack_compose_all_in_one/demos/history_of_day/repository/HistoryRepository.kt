package com.example.jetpack_compose_all_in_one.demos.history_of_day.repository

import com.example.jetpack_compose_all_in_one.demos.history_of_day.model.Dates
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.NetworkResult


interface HistoryRepository {
  suspend fun fetchHistory(): NetworkResult<List<Dates>>
}
