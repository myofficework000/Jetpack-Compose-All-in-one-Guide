package com.example.jetpack_compose_all_in_one.demos.tiktok

sealed class TikTokScreen(val route: String) {
    object Home : TikTokScreen("Home")
    object Discover : TikTokScreen("Discover")
    object Create : TikTokScreen("Create")
    object Inbox : TikTokScreen("Inbox")
    object Me : TikTokScreen("About")
    object Profile : TikTokScreen("Profile")
}