package com.example.jetpack_compose_all_in_one.demos.history_of_day.service

import com.example.jetpack_compose_all_in_one.demos.history_of_day.model.Dates
import retrofit2.http.GET

interface ApiService {

  @GET("/history-of-today")
  suspend fun getHistoryOfToday(): List<Dates>?

}