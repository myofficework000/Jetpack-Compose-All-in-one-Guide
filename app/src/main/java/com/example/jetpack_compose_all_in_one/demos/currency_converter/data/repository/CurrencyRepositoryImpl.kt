package com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.api.CurrencyApiService
import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val apiService: CurrencyApiService) : CurrencyRepository {
    override suspend fun getEURToJPY(): Result<CurrencyResponse> {
        return try {
            val response = apiService.getEURToJPY()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(RuntimeException("Failed to fetch data"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}