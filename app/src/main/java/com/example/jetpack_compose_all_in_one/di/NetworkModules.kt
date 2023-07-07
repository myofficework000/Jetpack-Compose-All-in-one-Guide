package com.example.jetpack_compose_all_in_one.di

import android.content.Context
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model.DogApiService
import com.example.jetpack_compose_all_in_one.features.domain_search.ApiDomainSearch
import com.example.jetpack_compose_all_in_one.features.login_style_1.ApiLoginService
import com.example.jetpack_compose_all_in_one.features.news_sample.data.remote.NewsApiInterceptor
import com.example.jetpack_compose_all_in_one.features.news_sample.data.remote.NewsService
import com.example.jetpack_compose_all_in_one.features.newsapi.data.remote.NewsApiService
import com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI.APIService
import com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI.repository.RemoteRepository
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogApiService
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxService
import com.example.jetpack_compose_all_in_one.features.swipe_cards.ApiQuotes
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.APIMovies
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbApiInterceptor
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.AuthInterceptor
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.ChatGPTApiServices
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.repository.ChatGPTRemoteRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.utils.Constants.CHAT_GPT_BASE_URL
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.ApiServiceCurrencyExchange
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.utils.CurrencyExchangeConstants
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.data.remote.GitHubService
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.Constants.QUOTES_BASE_URL
import com.example.jetpack_compose_all_in_one.utils.Constants.RANDOM_DOG_BASE_URL
import com.example.jetpack_compose_all_in_one.utils.Constants.RANDOM_FOX_BASE_URL
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
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.example.jetpack_compose_all_in_one.third_party_lib.stripe.ApiStripe
import com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.api.YelpAPI
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.logging.HttpLoggingInterceptor

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
    @NewsOrgAPI
    fun provideNewsOrgApiService() =
        Retrofit.Builder()
            .baseUrl(Constants.NEWS_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)

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
    @RandomDogAPI
    fun provideRandomDogApi() = Retrofit.Builder()
        .baseUrl(RANDOM_DOG_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(RandomDogApiService::class.java)

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

    @Provides
    @Singleton
    @RandomFoxAPI
    fun randomFoxAPI() =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RANDOM_FOX_BASE_URL)
            .build()
            .create(RandomFoxService::class.java)


    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .callTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideChatGptAPI(
        converter: Converter.Factory,
        client: OkHttpClient
    ) =
        Retrofit.Builder()
            .addConverterFactory(converter)
            .baseUrl(CHAT_GPT_BASE_URL)
            .client(client)
            .build()
            .create(ChatGPTApiServices::class.java)

    @Provides
    @Singleton
    fun provideChatGPTRepository( chatGPTApiServices: ChatGPTApiServices) =
        ChatGPTRemoteRepository( chatGPTApiServices )

    @Provides
    @Singleton
    fun provideApiStripe(
        @ApplicationContext context: Context,
        converter: Converter.Factory
    ) = Retrofit.Builder()
        .baseUrl(
            context.resources.getString(R.string.STRIPE_BACKEND_BASEURL)
                .takeIf { it.contains("http") } ?: "https://www.google.com"
        )
        .addConverterFactory(converter)
        .build()
        .create<ApiStripe>()

    @Provides
    @Singleton
    @GithubAPI
    fun provideApiGitHub() = Retrofit.Builder()
        .baseUrl(Constants.GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<GitHubService>()

    @Provides
    @Singleton
    @YelpAPIAnnotation
    fun provideYelpApi() = Retrofit.Builder()
        .baseUrl(Constants.YELP_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<YelpAPI>()


    @Provides
    @Singleton
    @CurrencyExchange
    fun provideCurrency(): ApiServiceCurrencyExchange {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(CurrencyExchangeConstants.BASE_UEL)
            .build()
            .create<ApiServiceCurrencyExchange>()
    }
}