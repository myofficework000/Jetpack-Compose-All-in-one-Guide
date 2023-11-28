package com.example.jetpack_compose_all_in_one.demos.news_app.interceptors

import com.example.jetpack_compose_all_in_one.demos.news_app.model.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val actualRequest = chain.request()

        val newUrl =
            actualRequest.url.newBuilder()
                .addQueryParameter("apiKey", Constants.NEWS_API_KEY)
                .build()

        val modifiedRequest = actualRequest.newBuilder().url(newUrl).build()
        return chain.proceed(modifiedRequest)
    }
}