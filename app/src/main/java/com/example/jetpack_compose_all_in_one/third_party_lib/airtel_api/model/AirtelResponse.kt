package com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.model

import com.google.gson.annotations.SerializedName

data class AirtelResponse(
    @SerializedName("country")
    val country: String = "",
    @SerializedName("city")
    val city: String = "" ,
    @SerializedName("asn")
    val asn: String = "",
    @SerializedName("lat")
    val lat: Float = 0f,
    @SerializedName("lon")
    val lon: Float = 0f
)