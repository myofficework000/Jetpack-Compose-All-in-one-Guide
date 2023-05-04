package com.example.jetpack_compose_all_in_one.ui.views.news_ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.di.NewsAPI
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.INewsRepository
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    @NewsAPI private val newsRepository: INewsRepository
) : ViewModel(){
    private val disposable = CompositeDisposable()

    private val _latestNewsResponse = MutableLiveData<LatestNewsResponse>()
    val latestNewsResponse : LiveData<LatestNewsResponse>
        get() = _latestNewsResponse

    private val _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() = _error

    fun getLatestNews(){
        disposable.add(
            newsRepository.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    _latestNewsResponse.value = res
                }, {
                    _error.value = it.message
                })
        )
    }

    init {
        latestNewsResponse.observeForever {
            Log.i("NewsViewModel", "Latest news response received: $it")
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}

