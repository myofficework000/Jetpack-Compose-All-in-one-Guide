package com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse

interface CurrencyRepository {
    suspend fun getEURToJPY(): Result<CurrencyResponse>
}