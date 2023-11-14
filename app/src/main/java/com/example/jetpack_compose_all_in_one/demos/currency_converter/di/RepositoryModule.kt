package com.example.jetpack_compose_all_in_one.demos.currency_converter.di

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.api.CurrencyApiService
import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository.CurrencyRepository
import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository.CurrencyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCurrencyRepository(apiService: CurrencyApiService) : CurrencyRepository = CurrencyRepositoryImpl(apiService)
}