package com.example.jetpack_compose_all_in_one.features.swipe_cards

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.di.QuotesAPI
import com.example.jetpack_compose_all_in_one.utils.InfiniteList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteSwipeViewModel @Inject constructor(
    @QuotesAPI private val apiQuotes: ApiQuotes,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    val currentQuotes = mutableStateListOf<QuotesResponse>()
    private val newQuotes = mutableListOf<QuotesResponse>()

    private var initialized = false
    val quotesList = InfiniteList{
        useNewQuotes()
        it.clear()
        it.addAll(currentQuotes)
    }

    init { refreshQuotes(10) }

    private fun refreshQuotes(limit: Int = 1) {
        viewModelScope.launch(ioDispatcher) {
             apiQuotes.getQuotes(limit).body()?.apply {
                 newQuotes.clear()
                 newQuotes.addAll(this)
                 if (!initialized) {
                     initialized = true
                     useNewQuotes()
                     quotesList.addAll(currentQuotes)
                 }
             }
        }
    }

    fun useNewQuotes() {
        currentQuotes.clear()
        currentQuotes.addAll(newQuotes)
        refreshQuotes(currentQuotes.size)
    }
}