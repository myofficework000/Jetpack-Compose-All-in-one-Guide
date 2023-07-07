package com.example.jetpack_compose_all_in_one.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TMDBAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QuotesAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsOrgAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DogAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RandomDogAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RandomFoxAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GithubAPI