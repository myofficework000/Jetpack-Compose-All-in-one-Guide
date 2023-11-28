package com.example.jetpack_compose_all_in_one.demos.news_app.model.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.demos.news_app.intent.RemoteNewsIntent
import com.example.jetpack_compose_all_in_one.demos.news_app.model.statemodel.NewsState
import com.example.jetpack_compose_all_in_one.demos.news_app.model.statemodel.RemoteNewsState
import com.example.jetpack_compose_all_in_one.di.MviNewsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteNewsViewModel @Inject constructor(private val myState: NewsState) : ViewModel() {

    private val _state = mutableStateOf<RemoteNewsState>(RemoteNewsState.Idle)
    val state: State<RemoteNewsState> = _state

    fun processIntent(intent: RemoteNewsIntent) {
        viewModelScope.launch {
            val curState = myState.processIntent(intent, _state.value)
            if (curState != null) {
                _state.value = curState
            } else {
                Log.e(TAG, "current state null")
            }
        }
    }

    companion object {
        private val TAG = this.javaClass.name
    }
}