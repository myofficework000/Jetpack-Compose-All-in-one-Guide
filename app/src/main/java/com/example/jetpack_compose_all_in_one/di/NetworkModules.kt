package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.features.dog_api.model.DogApiService
import com.example.jetpack_compose_all_in_one.features.domain_search.ApiDomainSearch
import com.example.jetpack_compose_all_in_one.features.login_style_1.ApiLoginService
import com.example.jetpack_compose_all_in_one.features.news_sample.data.remote.NewsApiInterceptor
import com.example.jetpack_compose_all_in_one.features.news_sample.data.remote.NewsService
import com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI.APIService
import com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI.repository.RemoteRepository
import com.example.jetpack_compose_all_in_one.features.swipe_cards.ApiQuotes
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.APIMovies
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbApiInterceptor
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.Constants.QUOTES_BASE_URL
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Converter.Factory
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
    fun provideBaseUrl(): String {
        return QUOTES_BASE_URL
    }

    @Singleton
    @Provides
    fun provideRXJavaAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        callAdapter: CallAdapter.Factory,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: APIService): RemoteRepository {
        return RemoteRepository(apiService)
    }

    @Provides
    @Singleton
    @NewsAPI
    fun provideNewsApiService() =
        Retrofit.Builder()
            .baseUrl(Constants.NEWS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(NewsApiInterceptor()).build())
            .build()
            .create<NewsService>()

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
    @QuotesAPI
    fun provideApiQuotes() = Retrofit.Builder()
        .baseUrl(QUOTES_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<ApiQuotes>()

    @Provides
    @Singleton
    @DogAPI
    fun provideDogApi() = Retrofit.Builder()
        .baseUrl("https://random.dog/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApiService::class.java)

    @Provides
    @Singleton
    fun provideApiDomainSearch() = Retrofit.Builder()
        .baseUrl("https://api.domainsdb.info/v1/domains/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiDomainSearch::class.java)

    // This api is fake atm.
    @Provides
    @Singleton
    fun provideApiLogin() = Retrofit.Builder()
        .baseUrl("https://you.need.therapy")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiLoginService::class.java)

    @Provides
    @Singleton
    fun provideIoDispatcher() = Dispatchers.IO


    @Provides
    @Singleton
    fun provideRTDatabase() = Firebase.database
}