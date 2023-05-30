package com.example.jetpack_compose_all_in_one.features.random_fox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxImpl
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxResponse
import com.example.jetpack_compose_all_in_one.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomFoxViewModel @Inject constructor(private val repository: RandomFoxImpl) : ViewModel() {
    private val _foxStateCoroutines = MutableLiveData<RandomFoxResponse>()
    val foxStateCoroutines: LiveData<RandomFoxResponse> = _foxStateCoroutines

    private var _foxStateFlow = MutableStateFlow(
        RandomFoxResponse()
    )
    val foxStateFlow = _foxStateFlow.asStateFlow()

    private val _foxStateRxjava = MutableLiveData<RandomFoxResponse>()
    val foxStateRxJava = _foxStateRxjava

    private val _errorState = MutableLiveData<String>()
    val errorState = _errorState

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState = _loadingState

    init {
        fetchRandomFoxUsingCoroutines()
        fetchRandomFoxUsingRxJava()
        fetchRandomFoxUsingFlow()
    }

    fun fetchRandomFoxUsingCoroutines() = viewModelScope.launch {
        when (val resultState = repository.getRandomFoxCoroutines()) {
            is ResultState.Success -> {
                _foxStateCoroutines.value = resultState.body
            }

            is ResultState.Error -> {
                _errorState.value = resultState.errorMessage
            }

            is ResultState.Loading -> {
                _loadingState.value = true
            }

            else -> {
                _loadingState.value = false
            }
        }

    }

    fun fetchRandomFoxUsingFlow() = viewModelScope.launch {
        repository.getRandomFoxFlow().collectLatest { resultState ->
            when (resultState) {
                is ResultState.Success -> {
                    resultState.body?.let {
                        _foxStateFlow.value = it
                    }

                }

                is ResultState.Error -> {
                    _errorState.value = resultState.errorMessage
                }

                is ResultState.Loading -> {
                    _loadingState.value = true
                }

                else -> {
                    _loadingState.value = false
                }
            }

        }
    }

    fun fetchRandomFoxUsingRxJava() =
        repository.getRandomFoxRxJava()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _foxStateRxjava.value = response
            },
                { error ->
                    _errorState.value = error.localizedMessage

                })

}