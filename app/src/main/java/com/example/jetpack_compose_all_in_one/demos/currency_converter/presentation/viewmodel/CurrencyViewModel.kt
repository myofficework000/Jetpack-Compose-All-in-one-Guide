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

/**
 * ViewModel responsible for managing the currency conversion logic and UI state.
 *
 * @param repository The repository used to fetch currency exchange rates.
 */
@HiltViewModel
class CurrencyViewModel @Inject constructor(private val repository: CurrencyRepository) :
    ViewModel() {

    // Mutable state representing the current UI state
    private val _state = mutableStateOf<CurrencyState>(CurrencyState.Idle)
    val state: State<CurrencyState> = _state

    // Mutable state flow to collect user input
    private val userInputFlow = MutableStateFlow("")

    // Mutable state for storing the conversion rate from EUR to JPY
    private var jpyConversionRate by mutableStateOf<Double?>(null)

    init {
        // Start observing user input
        observeUserInput()
    }

    // Function to observe user input flow
    private fun observeUserInput() {
        userInputFlow
            .debounce(500) // Add debounce to reduce frequency of updates
            .filter { it.isNotEmpty() } // Filter out empty input
            .onEach { query ->
                fetchCurrency(query) // Fetch currency data based on user input
            }
            .launchIn(viewModelScope) // Launch the flow in the viewModelScope
    }

    // Function to fetch currency data based on user input
    private fun fetchCurrency(query: String) {
        viewModelScope.launch {
            // Update UI state to indicate loading
            _state.value = CurrencyState.Loading
            // Fetch currency data from the repository
            val result = repository.getEURToJPY()
            // Update UI state based on the result
            _state.value = if (result.isSuccess) {
                // If successful, update conversion rate and set state to success
                jpyConversionRate = result.getOrNull()?.jpy
                CurrencyState.Success(result.getOrNull()!!)
            } else {
                // If failed, set state to error with appropriate message
                CurrencyState.Error(result.exceptionOrNull()?.message ?: "Unknown Error")
            }
        }
    }

    // Function to handle user input change
    fun onUserInputChanged(input: String) {
        userInputFlow.value = input // Update the value of userInputFlow
    }

    // Function to calculate converted amount
    fun calculateConvertedAmount(input: String): Double {
        val inputAmount =
            input.toDoubleOrNull() ?: return 0.0 // Convert input to double or return 0.0 if failed
        val rate =
            jpyConversionRate ?: return 0.0 // Get conversion rate, or return 0.0 if not available
        return inputAmount * rate // Calculate and return converted amount
    }

    // Function to handle intents
    fun handleIntent(intent: CurrencyIntent) {
        when (intent) {
            is CurrencyIntent.CurrencyInputChanged -> {
                onUserInputChanged(intent.input) // Handle currency input change
            }
        }
    }
}
