package com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI

import com.example.jetpack_compose_all_in_one.utils.Constants.RANDOM_ENDPOINT
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {

    @GET(RANDOM_ENDPOINT)
    fun getRandomQuote(): Single<QuoteResponse>
}