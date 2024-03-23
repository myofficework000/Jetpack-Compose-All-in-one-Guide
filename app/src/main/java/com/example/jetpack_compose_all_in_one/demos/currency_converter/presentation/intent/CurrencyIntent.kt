package com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent

/**
 * Sealed class representing intents related to currency operations.
 */
sealed class CurrencyIntent {

    /**
     * Intent representing a change in the input currency value.
     *
     * @property input The new input currency value.
     */
    data class CurrencyInputChanged(val input: String): CurrencyIntent()
}
