package com.example.jetpack_compose_all_in_one.ui.views.quotes_ui

import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI.QuoteResponse
import com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
): ViewModel(){
    val quoteResponse: LiveData<QuoteResponse> = remoteRepository.quoteResponse
    lateinit var disposable: Disposable

    fun getQuote(){
        disposable = remoteRepository.getQuote()
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    fun showNotification(){
        val notification = notificationBuilder
            .setContentText(quoteResponse.value?.content)
            .build()
        notificationManager.notify(1,notification)
    }
    override fun onCleared() {
        super.onCleared()
        if(this::disposable.isInitialized){
            disposable.dispose()
        }
    }
}