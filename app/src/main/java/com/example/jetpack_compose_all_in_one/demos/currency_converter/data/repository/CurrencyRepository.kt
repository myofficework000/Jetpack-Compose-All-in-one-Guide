package com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse

/**
 * Interface representing a currency repository.
 * Implementations of this interface provide methods to fetch currency exchange rates.
 */
interface CurrencyRepository {
    /**
     * Asynchronously retrieves the exchange rate from Euro (EUR) to Japanese Yen (JPY).
     *
     * @return [Result] object containing the [CurrencyResponse] if the operation is successful,
     *         or an error if the operation fails.
     */
    suspend fun getEURToJPY(): Result<CurrencyResponse>
}
