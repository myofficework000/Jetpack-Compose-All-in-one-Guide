package com.example.jetpack_compose_all_in_one.android_architectures.mvi.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DogApiResponse(
    @SerializedName("message")
    val url: String = ""
) : Serializable