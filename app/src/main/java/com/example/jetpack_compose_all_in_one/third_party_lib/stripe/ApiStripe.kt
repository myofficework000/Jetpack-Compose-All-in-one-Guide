package com.example.jetpack_compose_all_in_one.third_party_lib.stripe

import retrofit2.Response
import retrofit2.http.POST

/*
    If it's not working, remember to provide a new base URL in local.properties.
    local.properties.default stores an expired link, so it's only a reference.
    The backend server only lives for 5 days, so change accordingly.
 */
interface ApiStripe {
    @POST("checkout")
    suspend fun checkout(): Response<StripeIntentResponse>
}

// https://glitch.com/edit/#!/remix/stripe-mobile-payment-sheet-automatic
/*
* This is the link used to generate a new backend project.
* Add a publishable key and client secret IN TEST MODE to the field
*   and go to the Preview tab to get the base URL.
* */