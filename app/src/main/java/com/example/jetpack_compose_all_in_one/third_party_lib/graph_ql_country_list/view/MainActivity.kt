package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.GraphQlBuilder
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.Repository
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.view.ui.theme.JetpackComposeAllInOneTheme
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.viewmodel.ContinentsViewModel
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {

    private val viewModel: ContinentsViewModel by viewModels {
        com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.viewmodel.ViewModelFactory(
            Repository(GraphQlBuilder.provideApolloClient(OkHttpClient()))
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

