package com.example.jetpack_compose_all_in_one.demos.tiktok

import com.example.jetpack_compose_all_in_one.data.model.Album

sealed class TiktokHomeInteractionEvents {
    data class OpenProfile(val album: Album) : TiktokHomeInteractionEvents()
    data class ShareVideo(val album: Album) : TiktokHomeInteractionEvents()
    object OpenComments : TiktokHomeInteractionEvents()
}