package com.example.jetpack_compose_all_in_one.features.weather_sample.model.remote

import com.example.jetpack_compose_all_in_one.features.weather_sample.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private lateinit var retrofit: Retrofit

    fun getRetrofit(): Retrofit{
        if(!this::retrofit.isInitialized){
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }
}