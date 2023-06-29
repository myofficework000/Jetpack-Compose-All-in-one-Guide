package com.example.jetpack_compose_all_in_one.utils

import com.google.android.gms.maps.model.LatLng

object Constants {
    const val SUCCESS_RESULT_WITH_DATA = """
            {
    "message": "https://images.dog.ceo/breeds/sharpei/noel.jpg",
    "status": "success"
}
        """
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

    const val RANDOM_WOOF_BASE_URL = "https://random.dog/"
    const val RANDOM_DOG_BASE_URL = "https://dog.ceo/api/breeds/image/"
    const val RANDOM_FOX_BASE_URL = "https://randomfox.ca/"

    const val ROOMDB_DBNAME = "allInOne"

    val MAP_POS_BIGBEN = LatLng(51.5007761, -0.124546)
    const val MAP_DEFAULT_ZOOM_1 = 17.54f
    const val LOCATION_UPDATE_INTERVAL = 10000L

    const val NEWS_API_URL = "https://newsapi.org/v2/"
    const val HEADLINES_NEWS = "top-headlines"
    const val NEWS_API_COUNTRY = "us"
    const val NEWS_API_KEY = "c7be7d16a533454a814230157ca0b726"
    const val YOUTUBE_URL = "https://www.youtube.com"

    // As the name suggests, it's perfectly safe to put the key here.
    const val STRIPE_PUBLISHABLE_KEY = "pk_test_51NDWcxAuGpza3cNLRCDyClOLtcCw2dw7lEBsS9beNH8Dy83oO1Swwe6cXwXhvibkQ4DbhZkWbkXIszTX8RA8NZZy00eEaRFF1Z"
}