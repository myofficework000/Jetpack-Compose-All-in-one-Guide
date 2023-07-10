package com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.repository

import com.example.jetpack_compose_all_in_one.di.CurrencyExchange
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.ApiServiceCurrencyExchange
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(@CurrencyExchange private val apiServiceCurrencyExchange: ApiServiceCurrencyExchange) :
    CurrencyIRepository {

    override suspend fun getCurrencies() = apiServiceCurrencyExchange.getCurrencyData()
}