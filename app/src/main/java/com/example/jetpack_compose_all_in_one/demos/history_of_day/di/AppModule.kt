package com.example.jetpack_compose_all_in_one.demos.history_of_day.di

import com.example.jetpack_compose_all_in_one.demos.history_of_day.repository.HistoryRepository
import com.example.jetpack_compose_all_in_one.demos.history_of_day.repository.HistoryRepositoryImpl
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

  @Singleton
  @Provides
  @Named("history_okhttp")
  fun getOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor { chain ->
      val request: Request = chain.request().newBuilder()
        .addHeader("X-RapidAPI-Key", "de2e0be4e3msh550ca3ddc0e3cebp155babjsnfe507ee4ddaa")
        .addHeader("X-RapidAPI-Host", "current-affairs-of-india.p.rapidapi.com").build()
      chain.proceed(request)
    }.connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build()
  }

  @Singleton
  @Provides
  @Named("history_retrofit")
  fun getRetrofit(@Named("history_okhttp") okhttp: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      //make it constant
      .baseUrl("https://current-affairs-of-india.p.rapidapi.com/")
      .client(okhttp)
      .build()
  }

  @Singleton
  @Provides
  @Named("history_api")
  fun getAPiService(@Named("history_retrofit") retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }

  @Provides
  fun getRepository(@Named("history_api") apiService: ApiService): HistoryRepository {
    return HistoryRepositoryImpl(apiService)
  }
}