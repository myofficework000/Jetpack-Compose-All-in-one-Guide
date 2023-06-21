package com.example.jetpack_compose_all_in_one.android_architectures.mvp.model.data

data class DogResponse(
    val fileSizeBytes: Int,
    val url: String
) {
    companion object{
        val empty = DogResponse(0,"")
    }
}