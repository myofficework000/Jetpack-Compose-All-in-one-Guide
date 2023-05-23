package com.example.jetpack_compose_all_in_one.features.news_sample.repository

import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.di.NewsAPI
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import com.example.jetpack_compose_all_in_one.features.news_sample.data.remote.NewsService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor(
    @NewsAPI private val newsService: NewsService
) : INewsRepository{
    private val cachedNewsByKeyword = hashMapOf<String, LatestNewsResponse>()

    override fun getLatestNews(): Single<LatestNewsResponse> {
        return newsService.getLatestNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchNews(
        keywords: String,
        start_date: String,
        end_date: String,
        category: String?,
        country: String?,
        language: String?
    ) = if (cachedNewsByKeyword.containsKey(keywords)) Single.just(cachedNewsByKeyword[keywords]!!)
    else newsService.searchNews(
        keywords,
        start_date,
        end_date,
        category,
        country,
        language
    ).map { // It's not mapping anything. It's just to intercept the result.
        if (it.status == "ok") cachedNewsByKeyword[keywords] = it
        it
    }

    override val latestNewsResponse = MutableLiveData<LatestNewsResponse>()
}