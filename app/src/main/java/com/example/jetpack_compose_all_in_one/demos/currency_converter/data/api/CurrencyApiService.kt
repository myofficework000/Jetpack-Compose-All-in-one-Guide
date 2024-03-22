package com.example.jetpack_compose_all_in_one.demos.currency_converter.data.api

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface for accessing a currency conversion API service.
 * This interface defines methods to retrieve currency conversion rates.
 */
interface CurrencyApiService {

    /**
     * Companion object containing constant values for the CurrencyApiService interface.
     */
    companion object {
        /**
         * The base URL for the currency conversion API.
         */
        const val CURRENCY_API_URL = "/gh/fawazahmed0/currency-api@1/latest/currencies/eur/jpy.json"
    }

    /**
     * Retrieves the latest exchange rate from Euro (EUR) to Japanese Yen (JPY).
     *
     * @return A [Response] object containing the currency conversion response.
     */
    @GET(CURRENCY_API_URL)
    suspend fun getEURToJPY(): Response<CurrencyResponse>
}

