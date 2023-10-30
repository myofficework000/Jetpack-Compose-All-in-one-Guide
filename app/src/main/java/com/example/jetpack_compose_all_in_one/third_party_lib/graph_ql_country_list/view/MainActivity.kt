package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.apollographql.apollo3.ApolloClient
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.CountriesRepository
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.CountriesRepositoryImpl
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.view.ui.theme.JetpackComposeAllInOneTheme
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.viewmodel.ContinentsViewModel
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ContinentsViewModel> {
        ViewModelFactory(
            CountriesRepositoryImpl(
                ApolloClient.Builder()
                    .serverUrl("https://countries.trevorblades.com/")
                    .build()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAllInOneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContinentsScreen(viewModel)
                }
            }
        }
    }
}

