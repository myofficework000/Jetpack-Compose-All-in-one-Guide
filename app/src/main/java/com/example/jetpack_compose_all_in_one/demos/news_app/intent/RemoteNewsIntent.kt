package com.example.jetpack_compose_all_in_one.demos.news_app.intent

sealed class RemoteNewsIntent {
    object GetNewsIntent : RemoteNewsIntent()
}