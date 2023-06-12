package com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote

import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private lateinit var retrofit: Retrofit

    fun getRetrofit(): Retrofit{
        if(!this::retrofit.isInitialized){
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                        )
                        .build()
                )
                .build()
        }

        return retrofit
    }
}