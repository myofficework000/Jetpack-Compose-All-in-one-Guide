package com.example.jetpack_compose_all_in_one.third_party_lib.stripe

import retrofit2.Response
import javax.inject.Inject

class StripeRepositoryImpl @Inject constructor(
    private val apiStripe: ApiStripe
): StripeRepository {
    override suspend fun retrieveClientSecret(): Response<StripeIntentResponse> =
        apiStripe.checkout()
}

interface StripeRepository {
    suspend fun retrieveClientSecret(): Response<StripeIntentResponse>
}