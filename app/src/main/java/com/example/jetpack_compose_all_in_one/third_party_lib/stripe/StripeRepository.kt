package com.example.jetpack_compose_all_in_one.third_party_lib.stripe

import javax.inject.Inject

class StripeRepository @Inject constructor(

) {
    fun createPaymentIntent(): VeryVeryFakeIntent {
        return VeryVeryFakeIntent()
    }
}

// Until a backend is setup to send client secret,
//      use this as a placeholder
class VeryVeryFakeIntent{
    val clientSecret = ""
}