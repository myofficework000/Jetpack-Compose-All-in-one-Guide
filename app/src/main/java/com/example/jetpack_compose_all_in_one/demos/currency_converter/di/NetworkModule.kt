package com.example.jetpack_compose_all_in_one.demos.currency_converter.di

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.api.CurrencyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dagger Hilt module for providing network-related dependencies.
 * This module provides the implementation of the [CurrencyApiService].
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    /**
     * Provides an instance of [CurrencyApiService] for making network calls related to currency data.
     *
     * @return An instance of [CurrencyApiService].
     */
    @Provides
    fun provideApiService(): CurrencyApiService {
        // Configure and build Retrofit instance
        return Retrofit.Builder()
            .baseUrl("https://cdn.jsdelivr.net") // Base URL for the API
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter factory for JSON serialization
            .build()
            .create(CurrencyApiService::class.java) // Create an instance of CurrencyApiService interface
    }
}
