package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.api.ApiDogCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.repository.DogRepositoryImplCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.repositories.DogRepositoryCleanCode
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CleanCodeNetworkModules {
    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/breeds/image/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<ApiDogCleanCode>()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class CleanCodeRepoModules {
    @Binds
    @ViewModelScoped
    abstract fun bindDogRepositoryCleanCode(
        impl: DogRepositoryImplCleanCode
    ): DogRepositoryCleanCode
}
