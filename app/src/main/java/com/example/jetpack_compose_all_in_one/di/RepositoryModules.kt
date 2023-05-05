package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.features.dog_api.model.DogRepository
import com.example.jetpack_compose_all_in_one.features.dog_api.model.IDogRepository
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.INewsRepository
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.NewsRepository
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.IMovieRepository
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.MovieRepository
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
    @DogAPI
    abstract fun bindDogRepository(impl:DogRepository): IDogRepository
}