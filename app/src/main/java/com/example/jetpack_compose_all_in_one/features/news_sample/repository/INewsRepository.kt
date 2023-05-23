package com.example.jetpack_compose_all_in_one.features.news_sample.repository

import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import io.reactivex.Single

interface INewsRepository {
    fun getLatestNews() : Single<LatestNewsResponse>
    val latestNewsResponse : MutableLiveData<LatestNewsResponse>
    fun searchNews(
        keywords: String,
        start_date: String,
        end_date: String,
        category: String? = null,
        country: String? = null,
        language: String? = null
    ): Single<LatestNewsResponse>
}