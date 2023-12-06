package com.example.jetpack_compose_all_in_one.demos.github_api.di

import com.example.jetpack_compose_all_in_one.demos.github_api.data.repository.GithubUserRepository
import com.example.jetpack_compose_all_in_one.demos.github_api.domain.GithubUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGithubUserRepository(githubUserRepositoryImpl: GithubUserRepositoryImpl): GithubUserRepository
}