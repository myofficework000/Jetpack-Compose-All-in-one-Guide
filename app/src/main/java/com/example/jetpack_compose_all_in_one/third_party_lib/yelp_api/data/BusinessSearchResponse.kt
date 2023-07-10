package com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.data

import com.google.gson.annotations.SerializedName

data class BusinessSearchResponse(
    @SerializedName("businesses") val businessList: List<Business>
)

data class Business (
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val url : String,
    @SerializedName("is_closed") val isClosed : Boolean,
    @SerializedName("ratings") val ratings : Float,
        )
