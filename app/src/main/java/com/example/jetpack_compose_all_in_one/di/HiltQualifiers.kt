package com.example.jetpack_compose_all_in_one.di

import javax.inject.Qualifier

/**
 * Annotation used to qualify the TMDB (The Movie Database) API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TMDBAPI

/**
 * Annotation used to qualify the Quotes API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QuotesAPI

/**
 * Annotation used to qualify the News API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsAPI

/**
 * Annotation used to qualify the News Organization API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsOrgAPI

/**
 * Annotation used to qualify the Dog API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DogAPI

/**
 * Annotation used to qualify the Random Dog API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RandomDogAPI

/**
 * Annotation used to qualify the Random Fox API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RandomFoxAPI

/**
 * Annotation used to qualify the GitHub API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GithubAPI

/**
 * Annotation used to qualify the Currency Exchange API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CurrencyExchange

/**
 * Annotation used to qualify the Yelp API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class YelpAPIAnnotation

/**
 * Annotation used to qualify the Airtel API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AirtelAPI

/**
 * Annotation used to qualify the SpaceX API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SpaceXAPI

/**
 * Annotation used to qualify the Country API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CountryAPI

/**
 * Annotation used to qualify the MVI (Model-View-Intent) News API.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MviNewsAPI
