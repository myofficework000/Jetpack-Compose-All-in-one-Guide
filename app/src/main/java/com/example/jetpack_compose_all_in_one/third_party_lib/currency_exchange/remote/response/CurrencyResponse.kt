package com.example.jetpack_compose_all_in_one.third_party_lib.CurrencyInfo_exchange.remote.response

import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.response.CurrencyInfo
import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("EUR") val eur: CurrencyInfo,
    @SerializedName("USD") val usd: CurrencyInfo,
    @SerializedName("JPY") val jpy: CurrencyInfo,
    @SerializedName("BGN") val bgn: CurrencyInfo,
    @SerializedName("CZK") val czk: CurrencyInfo,
    @SerializedName("DKK") val dkk: CurrencyInfo,
    @SerializedName("GBP") val gbp: CurrencyInfo,
    @SerializedName("HUF") val huf: CurrencyInfo,
    @SerializedName("PLN") val pln: CurrencyInfo,
    @SerializedName("RON") val ron: CurrencyInfo,
    @SerializedName("SEK") val sek: CurrencyInfo,
    @SerializedName("CHF") val chf: CurrencyInfo,
    @SerializedName("ISK") val isk: CurrencyInfo,
    @SerializedName("NOK") val nok: CurrencyInfo,
    @SerializedName("HRK") val hrk: CurrencyInfo,
    @SerializedName("RUB") val rub: CurrencyInfo,
    @SerializedName("TRY") val tryc: CurrencyInfo,
    @SerializedName("AUD") val aud: CurrencyInfo,
    @SerializedName("BRL") val brl: CurrencyInfo,
    @SerializedName("CAD") val cad: CurrencyInfo,
    @SerializedName("CNY") val cny: CurrencyInfo,
    @SerializedName("HKD") val hkd: CurrencyInfo,
    @SerializedName("IDR") val idr: CurrencyInfo,
    @SerializedName("ILS") val ils: CurrencyInfo,
    @SerializedName("INR") val inr: CurrencyInfo,
    @SerializedName("KRW") val krw: CurrencyInfo,
    @SerializedName("MXN") val mxn: CurrencyInfo,
    @SerializedName("MYR") val myr: CurrencyInfo,
    @SerializedName("NZD") val nzd: CurrencyInfo,
    @SerializedName("PHP") val php: CurrencyInfo,
    @SerializedName("SGD") val sgd: CurrencyInfo,
    @SerializedName("THB") val thb: CurrencyInfo,
    @SerializedName("ZAR") val zar: CurrencyInfo
)