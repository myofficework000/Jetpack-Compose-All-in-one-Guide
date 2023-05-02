package com.example.jetpack_compose_all_in_one.features.news_sample.data.remote

import com.example.jetpack_compose_all_in_one.utils.Constants.AUTHORIZATION
import com.example.jetpack_compose_all_in_one.utils.Constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
        currentRequest.addHeader(AUTHORIZATION, TOKEN)

        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}
