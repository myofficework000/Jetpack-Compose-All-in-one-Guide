package com.example.jetpack_compose_all_in_one.data.QuoteAPI.repository

import androidx.lifecycle.MutableLiveData
import com.example.jetpack_compose_all_in_one.data.QuoteAPI.QuoteResponse
import io.reactivex.disposables.Disposable

interface IRepository {
    fun getQuote(): Disposable
    val quoteResponse: MutableLiveData<QuoteResponse>
}