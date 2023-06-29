package com.example.jetpack_compose_all_in_one.third_party_lib.stripe


import com.google.gson.annotations.SerializedName

data class StripeIntentResponse(
    @SerializedName("customer")
    val customer: String,
    @SerializedName("ephemeralKey")
    val ephemeralKey: String,
    @SerializedName("paymentIntent")
    val paymentIntent: String,
    @SerializedName("publishableKey")
    val publishableKey: String
)