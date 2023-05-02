package com.example.jetpack_compose_all_in_one.features.login_style_1

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiLoginService {
    @POST("...")
    suspend fun login(
        @Body loginBody: LoginRequest
    ): Response<LoginResponse>

    @POST(".....")
    suspend fun register(
        @Body registerBody: RegisterRequest
    ): Response<LoginResponse>

    @GET("...")
    suspend fun validateEmail(
        @Query("email") email: String
    ): Response<Boolean>
}