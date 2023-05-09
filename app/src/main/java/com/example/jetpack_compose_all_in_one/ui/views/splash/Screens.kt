package com.example.jetpack_compose_all_in_one.ui.views.splash

sealed class Screen(val route: String){
    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
}
