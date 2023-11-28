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
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.repository.CurrencyIRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.repository.CurrencyRepositoryImpl
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.repo.AirtelRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.repo.AirtelRepositoryImpl
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

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {
    @Binds
    @Singleton
    @TMDBAPI
    abstract fun bindMovieRepository(impl: MovieRepository): IMovieRepository

    @Binds
    @Singleton
    @NewsAPI
    abstract fun bindNewsRepository(impl: NewsRepository): INewsRepository

    @Binds
    @Singleton
    @NewsOrgAPI
    abstract fun bindNewsOrgApiRepository(impl: NewsApiRepositoryImpl) : INewsApiRepository

    @Binds
    @Singleton
    @DogAPI
    abstract fun bindDogRepository(impl: DogRepository): IDogRepository

    @Binds
    @Singleton
    @RandomDogAPI
    abstract fun bindRandomDogRepository(impl: RandomDogRepository): IRandomDogRepository

    @Binds
    @Singleton
    abstract fun bindDomainSearchRepository(impl: DomainSearchRepository): IDomainSearchRepository

    @Binds
    @Singleton
    @RandomFoxAPI
    abstract fun bindRandomFoxRepository(impl: RandomFoxImpl): IRandomFoxRepository

    @Binds
    @Singleton
    abstract fun bindStripeRepository(impl: StripeRepositoryImpl): StripeRepository

    @Binds
    @Singleton
    abstract fun bindPreferenceRepository(impl: PreferenceRepositoryImpl): PreferenceRepository

    @Binds
    @Singleton
    @GithubAPI
    abstract fun bindGithubRepository(impl: GitHubRepositoryImpl): GitHubRepository

    @Binds
    @Singleton
    @CurrencyExchange
    abstract fun bindCurrencyRepository(impl: CurrencyRepositoryImpl) : CurrencyIRepository

    @Binds
    @Singleton
    @AirtelAPI
    abstract fun bindAirtelRepository(impl: AirtelRepositoryImpl): AirtelRepository

    @Binds
    @Singleton
    @SpaceXAPI
    abstract fun bindSpaceXLaunchesRepository(impl: SpaceXLaunchesRepositoryImpl): SpaceXLaunchesRepository

    @Binds
    @Singleton
    @CountryAPI
    abstract fun bindCountriesRepositoryRepository(impl: CountriesRepositoryImpl): CountriesRepository

    @Binds
    @Singleton
    @MviNewsAPI
    abstract fun bindMviNewsRepository(impl:RemoteNewsRepositoryImpl) : RemoteNewRepository

}