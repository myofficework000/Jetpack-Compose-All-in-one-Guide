package com.example.jetpack_compose_all_in_one.features.flows_demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowsViewModel @Inject constructor(): ViewModel() {
    private var value1 = 0
    private var value2 = 0

    private var flowValue = 0
    private var flowJob: Job? = null

    private val _stateFlow1 = MutableStateFlow(0)
    val stateFlow1 = _stateFlow1.asStateFlow()

    private val _sharedFlow1 = MutableSharedFlow<Int>()
    val sharedFlow1 = _sharedFlow1.asSharedFlow()

    fun flowLikeRiver() {
        flowJob = viewModelScope.launch {
            while (true) {
                delay(100)
                flowValue = (flowValue + 1) % 10
                _stateFlow1.emit(flowValue)
                _sharedFlow1.emit(flowValue)
            }
        }
    }

    fun stopTheRiver() {
        flowJob?.cancel()
        flowJob = null
    }

    fun addValue1(num: Int) {
        value1 += num
        viewModelScope.launch {
            _stateFlow1.emit(value1)
        }
    }

    fun addValue2(num: Int) {
        value2 += num
        viewModelScope.launch {
            _sharedFlow1.emit(value2)
        }
    }
}