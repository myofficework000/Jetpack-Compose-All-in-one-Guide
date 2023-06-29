package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.di

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.repository.WeatherRepositoryImpl
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}