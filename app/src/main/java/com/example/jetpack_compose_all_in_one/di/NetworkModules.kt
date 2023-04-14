package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.data.QuoteAPI.APIService
import com.example.jetpack_compose_all_in_one.data.QuoteAPI.repository.RemoteRepository
import com.example.jetpack_compose_all_in_one.data.tmdbapi.APIMovies
import com.example.jetpack_compose_all_in_one.data.tmdbapi.TmdbApiInterceptor
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {

    @Singleton
    @Provides
    fun provideBaseUrl(): String{
        return BASE_URL
    }

    @Singleton
    @Provides
    fun provideRXJavaAdapterFactory(): CallAdapter.Factory{
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideConverterFactory():Converter.Factory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        callAdapter: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService{
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: APIService): RemoteRepository{
        return RemoteRepository(apiService)
    }



    @Provides
    @Singleton
    @TMDBAPI
    fun provideApiMovies() = Retrofit.Builder()
        .baseUrl(Constants.tmdb_base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(TmdbApiInterceptor()).build())
        .build()
        .create<APIMovies>()

    @Provides
    @Singleton
    @TMDBAPI
    fun provideIoDispatcher() = Dispatchers.IO
}