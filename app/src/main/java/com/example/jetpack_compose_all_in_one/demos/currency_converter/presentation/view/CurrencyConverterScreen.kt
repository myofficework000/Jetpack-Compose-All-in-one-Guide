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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyIntent
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyState
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.viewmodel.CurrencyViewModel

@Composable
fun CurrencyFromToScreen(viewModel: CurrencyViewModel = hiltViewModel()) {
    val state by viewModel.state
    var text by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        viewModel.onUserInputChanged(text)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.handleIntent(CurrencyIntent.CurrencyInputChanged(it))
            },
            label = { Text("Enter EUR Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        when(state) {
            is CurrencyState.Loading -> CircularProgressIndicator()
            is CurrencyState.Success -> {
                val convertedAmount = viewModel.calculateConvertedAmount(text)
                Text("Converted amount: ${String.format("%.2f JPY", convertedAmount)}")
            }
            is CurrencyState.Error -> Text(text = "Error: ${(state as CurrencyState.Error).error}")
            else -> {}
        }
    }
}
