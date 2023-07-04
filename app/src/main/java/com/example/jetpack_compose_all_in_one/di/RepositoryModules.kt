package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model.DogRepository
import com.example.jetpack_compose_all_in_one.android_architectures.mvvm.model.IDogRepository
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
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository.GitHubRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.paging3.repository.GitHubRepositoryImpl
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
}