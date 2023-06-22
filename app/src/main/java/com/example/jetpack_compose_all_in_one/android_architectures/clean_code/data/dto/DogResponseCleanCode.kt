package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto


import com.google.gson.annotations.SerializedName

data class DogResponseCleanCode(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)