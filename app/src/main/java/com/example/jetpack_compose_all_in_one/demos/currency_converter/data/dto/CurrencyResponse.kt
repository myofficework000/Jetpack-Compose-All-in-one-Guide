package com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto

/**
 * Represents a response containing currency data.
 *
 * @property date The date of the response.
 * @property jpy The Japanese Yen value in the response.
 */
data class CurrencyResponse(
    val date: String, // Date of the response
    val jpy: Double   // Japanese Yen value in the response
)
