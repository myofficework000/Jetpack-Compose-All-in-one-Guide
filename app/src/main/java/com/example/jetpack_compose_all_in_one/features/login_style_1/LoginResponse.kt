package com.example.jetpack_compose_all_in_one.features.login_style_1

data class LoginResponse(
    val status: String,
    val user: LoginUser?
)

data class LoginUser(
    val userId: String,
    val username: String
)