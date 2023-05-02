package com.example.jetpack_compose_all_in_one.features.login_style_1

import androidx.compose.runtime.mutableStateOf

class LoginStateHolder {
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val rememberMe = mutableStateOf(false)
}