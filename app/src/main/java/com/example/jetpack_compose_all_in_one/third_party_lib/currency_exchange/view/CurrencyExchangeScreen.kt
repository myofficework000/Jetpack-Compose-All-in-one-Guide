package com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_all_in_one.third_party_lib.CurrencyInfo_exchange.remote.response.CurrencyResponse
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.response.CurrencyInfo
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.viewmodel.CurrencyExchangeViewModel


@Preview
@Composable
fun CurrencyExchangeScreen() {
    val viewModel: CurrencyExchangeViewModel = hiltViewModel()
    val currencies = viewModel.currencyResponseMutableLiveData.observeAsState()


    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Currency Table", style = MaterialTheme.typography.headlineLarge)

        currencies.value?.let { CurrencyTable(response = it) }
    }

}

@Composable
fun CurrencyRow(currency: CurrencyInfo) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = currency.toString(), style = MaterialTheme.typography.bodyMedium)
        Text(text = currency.name, style = MaterialTheme.typography.bodyMedium)
        Text(text = currency.symbol, style = MaterialTheme.typography.bodyMedium)
    }
}
@Composable
fun CurrencyTable(response: CurrencyResponse) {
    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
        item {
            Row() {
                Text(text = "Currency Code", style = MaterialTheme.typography.bodySmall)
                Text(text = "Name", style = MaterialTheme.typography.bodySmall)
                Text(text = "Symbol", style = MaterialTheme.typography.bodySmall)
                CurrencyRow(response.aud)
            }
        }

    }
}
