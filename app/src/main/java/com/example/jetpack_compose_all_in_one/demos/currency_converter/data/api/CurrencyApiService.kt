package com.example.jetpack_compose_all_in_one.demos.currency_converter.data.api

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApiService {

    @GET("/gh/fawazahmed0/currency-api@1/latest/currencies/eur/jpy.json")
    suspend fun getEURToJPY() : Response<CurrencyResponse>
}