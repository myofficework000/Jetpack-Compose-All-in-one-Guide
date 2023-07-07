package com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote

import com.example.jetpack_compose_all_in_one.third_party_lib.CurrencyInfo_exchange.remote.response.CurrencyResponse
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.utils.CurrencyExchangeConstants
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceCurrencyExchange {

    @GET(CurrencyExchangeConstants.END_PATH)
    suspend fun getCurrencyData() : Response<CurrencyResponse>
}