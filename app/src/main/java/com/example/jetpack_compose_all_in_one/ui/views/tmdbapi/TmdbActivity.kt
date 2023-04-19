package com.example.jetpack_compose_all_in_one.ui.views.tmdbapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TmdbActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeAllInOneTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PopularMoviesPage(vm = viewModel())
                }
            }
        }
    }
}