package com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.di

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.data.location.DefaultLocationTracker
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code_with_mvi_mvvm.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(
        defaultLocationTracker: DefaultLocationTracker
    ): LocationTracker
}