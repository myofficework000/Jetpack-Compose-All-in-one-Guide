package com.example.jetpack_compose_all_in_one.android_architectures.mvp.model.data

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("fileSizeBytes")
    val fileSizeBytes: Int,
    @SerializedName("url")
    val url: String
) {
    companion object{
        val empty = DogResponse(0,"")
    }
}