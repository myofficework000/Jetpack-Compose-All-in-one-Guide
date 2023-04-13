package com.example.jetpack_compose_all_in_one.data.QuoteAPI.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.data.QuoteAPI.APIService
import com.example.jetpack_compose_all_in_one.data.QuoteAPI.QuoteResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: APIService
): IRepository{
    override val quoteResponse = MutableLiveData<QuoteResponse>()
    override fun getQuote() = apiService.getRandomQuote()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({res ->
            quoteResponse.value = res
        },{
            Log.i("error",it.message.toString())
        })
}