package com.example.jetpack_compose_all_in_one.di

import com.example.jetpack_compose_all_in_one.data.tmdbapi.repository.IMovieRepository
import com.example.jetpack_compose_all_in_one.data.tmdbapi.repository.MovieRepository
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
}