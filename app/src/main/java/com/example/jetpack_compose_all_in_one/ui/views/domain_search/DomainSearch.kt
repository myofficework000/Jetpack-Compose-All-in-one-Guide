package com.example.jetpack_compose_all_in_one.ui.views.domain_search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.domain_search.DomainSearchViewModel
import com.example.jetpack_compose_all_in_one.ui.components.GradientTextField
import com.example.jetpack_compose_all_in_one.ui.components.SimpleIconButton
import com.example.jetpack_compose_all_in_one.ui.theme.WhiteToBlue20
import com.example.jetpack_compose_all_in_one.utils.ResultState

@Composable
fun DomainSearch(
    vm: DomainSearchViewModel
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GradientTextField(
                value = vm.searchBox.value,
                gradient = WhiteToBlue20,
                modifier = Modifier.weight(1f)
            ) {
                vm.searchBox.value = it
            }

            SimpleIconButton(iconResourceInt = R.drawable.baseline_search_24) {
                vm.searchDomain(vm.searchBox.value)
            }
        }

        Spacer(Modifier.height(32.dp))

        vm.searchResult.value.run{
            if (this is ResultState.Success && body != null) {
                LazyColumn (
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(
                        body.domains
                    ){
                        Text(it.domain)
                    }
                }
            }
        }
    }

}