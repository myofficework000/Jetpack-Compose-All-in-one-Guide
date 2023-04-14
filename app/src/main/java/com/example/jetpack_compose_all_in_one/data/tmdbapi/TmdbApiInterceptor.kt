package com.example.jetpack_compose_all_in_one.data.tmdbapi

import com.example.jetpack_compose_all_in_one.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class TmdbApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request().newBuilder().url(
                request().url().newBuilder().addQueryParameter(
                    "api_key", Constants.tmdb_api_key_temp
                ).build()
            ).build()
        )
    }
}