package com.example.jetpack_compose_all_in_one.utils

import com.google.android.gms.maps.model.LatLng

object Constants {
    const val BASE_URL_OLD = "https://api.quotable.io/"
    const val RANDOM_ENDPOINT = "random"
    const val QUOTES_BASE_URL = "https://api.quotable.io/"
    const val QUOTES_ENDPOINT = "quotes/random"

    const val NEWS_URL = "https://api.currentsapi.services/v1/"
    const val LATEST_NEWS = "latest-news"
    const val SEARCH_NEWS = "search"
    const val AUTHORIZATION = "Authorization"
    const val TOKEN = "fyb9N0FOdYPeGXY4g4aNi7rcObh0KxHDkH8rICryFTzJb_rC"

    const val tmdb_api_key_temp = "441f9a2f476e2d801a859a9955fe6188"
    const val tmdb_base_url = "https://api.themoviedb.org/3/movie/"
    const val tmdb_endpoint_popular = "popular"
    const val tmdb_image_base_url = "https://image.tmdb.org/t/p/original"

    const val ALARM_REQUEST_CODE = 100
    const val ROOMDB_DBNAME = "allInOne"

    val MAP_POS_BIGBEN = LatLng(51.5007761, -0.124546)
    const val MAP_DEFAULT_ZOOM_1 = 18.54f
    const val LOCATION_UPDATE_INTERVAL = 10000L

    const val HOME = "home"
}