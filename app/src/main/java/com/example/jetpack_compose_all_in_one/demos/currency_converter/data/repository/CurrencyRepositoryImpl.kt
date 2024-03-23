package com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.api.CurrencyApiService
import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse
import javax.inject.Inject


/**
 * Implementation of [CurrencyRepository] interface.
 * This class is responsible for fetching currency exchange rates.
 *
 * @param apiService The API service used to make network calls for currency data.
 */
class CurrencyRepositoryImpl @Inject constructor(private val apiService: CurrencyApiService) :
    CurrencyRepository {

    /**
     * Asynchronously retrieves the exchange rate from Euro (EUR) to Japanese Yen (JPY).
     *
     * @return [Result] object containing the [CurrencyResponse] if the operation is successful,
     *         or an error if the operation fails.
     */
    override suspend fun getEURToJPY(): Result<CurrencyResponse> {
        return try {
            // Make the network call to fetch EUR to JPY exchange rate
            val response = apiService.getEURToJPY()
            // Check if the response is successful and contains data
            if (response.isSuccessful && response.body() != null) {
                // If successful, return the currency response wrapped in a success Result
                Result.success(response.body()!!)
            } else {
                // If response is not successful or does not contain data, return a failure Result
                Result.failure(RuntimeException("Failed to fetch data"))
            }
        } catch (e: Exception) {
            // If an exception occurs during the operation, return a failure Result with the exception
            Result.failure(e)
        }
    }
}
