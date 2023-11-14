package com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse

sealed class CurrencyState {

    object Idle: CurrencyState()
    object Loading: CurrencyState()
    data class Success(val data: CurrencyResponse) : CurrencyState()
    data class Error(val error: String) : CurrencyState()
}