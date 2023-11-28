package com.example.jetpack_compose_all_in_one.demos.news_app.model.statemodel

import android.util.Log
import com.example.jetpack_compose_all_in_one.demos.news_app.intent.RemoteNewsIntent
import com.example.jetpack_compose_all_in_one.demos.news_app.model.repository.RemoteNewRepository
import com.example.jetpack_compose_all_in_one.di.MviNewsAPI
import javax.inject.Inject

class NewsState @Inject constructor(@MviNewsAPI private val repository: RemoteNewRepository) {

    suspend fun processIntent(intent: RemoteNewsIntent, state: RemoteNewsState): RemoteNewsState? {

        return when (intent) {
            RemoteNewsIntent.GetNewsIntent -> {
                try {
                    Log.e(TAG, "Fetching news from api")
                    val response = repository.getNews()
                    if (response.isSuccessful) {
                        Log.e(TAG, "Fetching news from api -- success")
                        response.body()?.articles?.let { RemoteNewsState.NewsList(it) }

                    } else {
                        Log.e(TAG, "Fetching news from api -- failed")
                        RemoteNewsState.Error("Error fetching news")
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e(TAG, "Fetching news from api -- Exception")
                    RemoteNewsState.Error("Error ${e.message}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "NewsState"
    }

}