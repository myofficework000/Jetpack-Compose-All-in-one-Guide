package com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyIntent
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyState
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.viewmodel.CurrencyViewModel

/**
 * Composable function representing the Currency From-To screen.
 * This function displays UI elements for entering the EUR amount and showing converted JPY amount.
 *
 * @param viewModel The [CurrencyViewModel] used to manage the UI logic and data.
 */
@Composable
fun CurrencyFromToScreen(viewModel: CurrencyViewModel = hiltViewModel()) {
    // Observe the state from the view model
    val state by viewModel.state

    // Mutable state for the text entered by the user
    var text by remember { mutableStateOf("") }

    // Effect that launches when the text changes
    LaunchedEffect(text) {
        // Notify the view model about the user input change
        viewModel.onUserInputChanged(text)
    }

    // Column composable to arrange UI elements vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField for entering the EUR amount
        TextField(
            value = text,
            onValueChange = {
                text = it
                // Notify the view model about the input change
                viewModel.handleIntent(CurrencyIntent.CurrencyInputChanged(it))
            },
            placeholder = { Text("Enter EUR Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        // Spacer to add vertical space
        Spacer(modifier = Modifier.height(16.dp))

        // Display different UI based on the state
        when (state) {
            // Loading state: Show a progress indicator
            is CurrencyState.Loading -> CircularProgressIndicator()
            // Success state: Display converted amount
            is CurrencyState.Success -> {
                // Calculate and display the converted amount
                val convertedAmount = viewModel.calculateConvertedAmount(text)
                Text("Converted amount: ${String.format("%.2f JPY", convertedAmount)}")
            }
            // Error state: Display error message
            is CurrencyState.Error -> Text(text = "Error: ${(state as CurrencyState.Error).error}")
            // Idle state: Do nothing
            else -> {}
        }
    }
}

