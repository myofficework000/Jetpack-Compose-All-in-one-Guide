package com.example.jetpack_compose_all_in_one.ui.views.quotes_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_compose_all_in_one.features.quotes_using_rx_java.QuoteAPI.QuoteResponse

@Composable
fun Quote(viewModel: QuoteViewModel = hiltViewModel()) {
    val response = viewModel.quoteResponse.observeAsState()
    LaunchedEffect(response){
        viewModel.getQuote()
    }
    response.value?.let{
        SetQuoteUI(it, clicked = {
            viewModel.getQuote()
            }
        )
    }
}

@Composable
fun SetQuoteUI(quoteResponse: QuoteResponse, clicked:() ->Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        quoteResponse.apply {
            QuoteCard(content,author)
        }
        Button(
            modifier = Modifier.padding(10.dp),
            onClick = clicked
        ) {
            Text(text = stringResource(com.example.jetpack_compose_all_in_one.R.string.get_quote_btn))
        }
    }
}