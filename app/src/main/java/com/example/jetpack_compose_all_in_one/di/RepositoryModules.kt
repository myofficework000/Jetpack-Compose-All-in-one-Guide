package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model.DogRepository
import com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model.IDogRepository
import com.example.jetpack_compose_all_in_one.demos.news_app.model.repository.RemoteNewRepository
import com.example.jetpack_compose_all_in_one.demos.news_app.model.repository.RemoteNewsRepositoryImpl
import com.example.jetpack_compose_all_in_one.features.domain_search.DomainSearchRepository
import com.example.jetpack_compose_all_in_one.features.domain_search.IDomainSearchRepository
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.INewsRepository
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.NewsRepository
import com.example.jetpack_compose_all_in_one.features.newsapi.repository.INewsApiRepository
import com.example.jetpack_compose_all_in_one.features.newsapi.repository.NewsApiRepositoryImpl
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.IRandomDogRepository
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogRepository
import com.example.jetpack_compose_all_in_one.features.random_fox.model.IRandomFoxRepository
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxImpl
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.IMovieRepository
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.MovieRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.repo.AirtelRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.repo.AirtelRepositoryImpl
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.repository.CurrencyIRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.repository.CurrencyRepositoryImpl
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.CountriesRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.CountriesRepositoryImpl
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository.GitHubRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository.GitHubRepositoryImpl
import com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.remote.SpaceXLaunchesRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.remote.SpaceXLaunchesRepositoryImpl
import com.example.jetpack_compose_all_in_one.third_party_lib.stripe.StripeRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.stripe.StripeRepositoryImpl
import com.example.jetpack_compose_all_in_one.utils.preferences.PreferenceRepository
import com.example.jetpack_compose_all_in_one.utils.preferences.PreferenceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module responsible for binding various repository implementations to their respective interfaces.
 * This module is installed in the [SingletonComponent], ensuring that the provided instances are singleton-scoped.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    /**
     * Binds [MovieRepository] implementation to the [IMovieRepository] interface.
     * This binding is annotated with [TMDBAPI] qualifier.
     * @return an instance of [IMovieRepository].
     */
    @Binds
    @Singleton
    @TMDBAPI
    abstract fun bindMovieRepository(impl: MovieRepository): IMovieRepository

    /**
     * Binds [NewsRepository] implementation to the [INewsRepository] interface.
     * This binding is annotated with [NewsAPI] qualifier.
     * @return an instance of [INewsRepository].
     */
    @Binds
    @Singleton
    @NewsAPI
    abstract fun bindNewsRepository(impl: NewsRepository): INewsRepository

    /**
     * Binds [NewsApiRepositoryImpl] implementation to the [INewsApiRepository] interface.
     * This binding is annotated with [NewsOrgAPI] qualifier.
     * @return an instance of [INewsApiRepository].
     */
    @Binds
    @Singleton
    @NewsOrgAPI
    abstract fun bindNewsOrgApiRepository(impl: NewsApiRepositoryImpl): INewsApiRepository

    /**
     * Binds [DogRepository] implementation to the [IDogRepository] interface.
     * This binding is annotated with [DogAPI] qualifier.
     * @return an instance of [IDogRepository].
     */
    @Binds
    @Singleton
    @DogAPI
    abstract fun bindDogRepository(impl: DogRepository): IDogRepository

    /**
     * Binds [RandomDogRepository] implementation to the [IRandomDogRepository] interface.
     * This binding is annotated with [RandomDogAPI] qualifier.
     * @return an instance of [IRandomDogRepository].
     */
    @Binds
    @Singleton
    @RandomDogAPI
    abstract fun bindRandomDogRepository(impl: RandomDogRepository): IRandomDogRepository

    /**
     * Binds [DomainSearchRepository] implementation to the [IDomainSearchRepository] interface.
     * @return an instance of [IDomainSearchRepository].
     */
    @Binds
    @Singleton
    abstract fun bindDomainSearchRepository(impl: DomainSearchRepository): IDomainSearchRepository

    /**
     * Binds [RandomFoxImpl] implementation to the [IRandomFoxRepository] interface.
     * This binding is annotated with [RandomFoxAPI] qualifier.
     * @return an instance of [IRandomFoxRepository].
     */
    @Binds
    @Singleton
    @RandomFoxAPI
    abstract fun bindRandomFoxRepository(impl: RandomFoxImpl): IRandomFoxRepository

    /**
     * Binds [StripeRepositoryImpl] implementation to the [StripeRepository] interface.
     * @return an instance of [StripeRepository].
     */
    @Binds
    @Singleton
    abstract fun bindStripeRepository(impl: StripeRepositoryImpl): StripeRepository

    /**
     * Binds [PreferenceRepositoryImpl] implementation to the [PreferenceRepository] interface.
     * @return an instance of [PreferenceRepository].
     */
    @Binds
    @Singleton
    abstract fun bindPreferenceRepository(impl: PreferenceRepositoryImpl): PreferenceRepository

    /**
     * Binds [GitHubRepositoryImpl] implementation to the [GitHubRepository] interface.
     * This binding is annotated with [GithubAPI] qualifier.
     * @return an instance of [GitHubRepository].
     */
    @Binds
    @Singleton
    @GithubAPI
    abstract fun bindGithubRepository(impl: GitHubRepositoryImpl): GitHubRepository

    /**
     * Binds [CurrencyRepositoryImpl] implementation to the [CurrencyIRepository] interface.
     * This binding is annotated with [CurrencyExchange] qualifier.
     * @return an instance of [CurrencyIRepository].
     */
    @Binds
    @Singleton
    @CurrencyExchange
    abstract fun bindCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyIRepository

    /**
     * Binds [AirtelRepositoryImpl] implementation to the [AirtelRepository] interface.
     * This binding is annotated with [AirtelAPI] qualifier.
     * @return an instance of [AirtelRepository].
     */
    @Binds
    @Singleton
    @AirtelAPI
    abstract fun bindAirtelRepository(impl: AirtelRepositoryImpl): AirtelRepository

    /**
     * Binds [SpaceXLaunchesRepositoryImpl] implementation to the [SpaceXLaunchesRepository] interface.
     * This binding is annotated with [SpaceXAPI] qualifier.
     * @return an instance of [SpaceXLaunchesRepository].
     */
    @Binds
    @Singleton
    @SpaceXAPI
    abstract fun bindSpaceXLaunchesRepository(impl: SpaceXLaunchesRepositoryImpl): SpaceXLaunchesRepository

    /**
     * Binds [CountriesRepositoryImpl] implementation to the [CountriesRepository] interface.
     * This binding is annotated with [CountryAPI] qualifier.
     * @return an instance of [CountriesRepository].
     */
    @Binds
    @Singleton
    @CountryAPI
    abstract fun bindCountriesRepositoryRepository(impl: CountriesRepositoryImpl): CountriesRepository

    /**
     * Binds [RemoteNewsRepositoryImpl] implementation to the [RemoteNewRepository] interface.
     * This binding is annotated with [MviNewsAPI] qualifier.
     * @return an instance of [RemoteNewRepository].
     */
    @Binds
    @Singleton
    @MviNewsAPI
    abstract fun bindMviNewsRepository(impl: RemoteNewsRepositoryImpl): RemoteNewRepository
}
