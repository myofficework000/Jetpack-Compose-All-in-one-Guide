package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.FindCountriesOfAContinentQuery
import com.example.jetpack_compose_all_in_one.GetContinentsQuery
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.viewmodel.ContinentsViewModel

@Composable
fun ContinentsScreen(viewModel: ContinentsViewModel) {
    val continents by viewModel.continents.observeAsState(emptyList())
    val countries by viewModel.countriesInContinent.observeAsState(emptyList())
    val error by viewModel.error.observeAsState(null)

    Column {
        error?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(continents) { continent ->
                ContinentItem(continent, onClick = {
                    viewModel.fetchCountriesOfSelectedContinent(continent.code)
                })
            }
        }

        Divider()

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(countries) { country ->
                CountryItem(country)
            }
        }
    }
}

@Composable
fun ContinentItem(continent: GetContinentsQuery.Continent, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(text = continent.name)
    }
}

@Composable
fun CountryItem(country: FindCountriesOfAContinentQuery.Country) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = country.name)
    }
}
