package com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.repository

import com.example.jetpack_compose_all_in_one.third_party_lib.CurrencyInfo_exchange.remote.response.CurrencyResponse
import retrofit2.Response

interface CurrencyIRepository {
    suspend fun getCurrencies(): Response<CurrencyResponse>
}