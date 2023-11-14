package com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository.CurrencyRepository
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyIntent
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val repository: CurrencyRepository) : ViewModel() {

    private val _state = mutableStateOf<CurrencyState>(CurrencyState.Idle)
    val state: State<CurrencyState> = _state

    private val userInputFlow = MutableStateFlow("")

    private var jpyConversionRate by mutableStateOf<Double?>(null)

    init {
        observeUserInput()
    }

    private fun observeUserInput() {
        userInputFlow
            .debounce(500)
            .filter { it.isNotEmpty() }
            .onEach { query ->
                fetchCurrency(query)
            }
            .launchIn(viewModelScope)
    }

    private fun fetchCurrency(query: String) {
        viewModelScope.launch {
            _state.value = CurrencyState.Loading
            val result = repository.getEURToJPY()
            _state.value = if (result.isSuccess) {
                jpyConversionRate = result.getOrNull()?.jpy
                CurrencyState.Success(result.getOrNull()!!)
            } else {
                CurrencyState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }

    fun onUserInputChanged(input: String) {
        userInputFlow.value = input
    }

    fun calculateConvertedAmount(input: String): Double {
        val inputAmount = input.toDoubleOrNull() ?: return 0.0
        val rate = jpyConversionRate ?: return 0.0
        return inputAmount * rate
    }

    fun handleIntent(intent: CurrencyIntent) {
        when (intent) {
            is CurrencyIntent.CurrencyInputChanged -> {
                onUserInputChanged(intent.input)
            }
        }

    }
}