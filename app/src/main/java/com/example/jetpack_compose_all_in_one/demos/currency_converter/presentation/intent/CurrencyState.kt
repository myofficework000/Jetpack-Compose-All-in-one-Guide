package com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse

/**
 * Sealed class representing various states related to currency operations.
 */
sealed class CurrencyState {

    /**
     * Represents the idle state, indicating no ongoing currency operation.
     */
    object Idle: CurrencyState()

    /**
     * Represents the loading state, indicating an ongoing currency data retrieval operation.
     */
    object Loading: CurrencyState()

    /**
     * Represents the success state, indicating successful retrieval of currency data.
     *
     * @property data The currency data retrieved successfully.
     */
    data class Success(val data: CurrencyResponse) : CurrencyState()

    /**
     * Represents the error state, indicating a failure in currency data retrieval.
     *
     * @property error A string describing the error that occurred.
     */
    data class Error(val error: String) : CurrencyState()
}
