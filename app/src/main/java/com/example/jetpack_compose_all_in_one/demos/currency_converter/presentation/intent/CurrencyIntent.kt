package com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent

sealed class CurrencyIntent {
    data class CurrencyInputChanged(val input: String): CurrencyIntent()
}