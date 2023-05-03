package com.example.jetpack_compose_all_in_one.features.news_sample.repository

import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface INewsRepository {
    fun getLatestNews() : Single<LatestNewsResponse>
    val latestNewsResponse : MutableLiveData<LatestNewsResponse>
}