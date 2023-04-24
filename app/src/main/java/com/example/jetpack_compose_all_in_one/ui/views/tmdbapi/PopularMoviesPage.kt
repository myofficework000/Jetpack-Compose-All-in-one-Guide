package com.example.jetpack_compose_all_in_one.ui.views.tmdbapi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponse
import com.example.jetpack_compose_all_in_one.utils.ResultState

@Composable
fun PopularMoviesPage(
    vm: MovieViewModel
) {
    val result by vm.popularMovies.collectAsState()

    LaunchedEffect(Unit) {
        vm.getPopularMovies(1)
    }

    when (result) {
        is ResultState.Success -> {
            // The API doesn't return a null on success, so this should be fine.
            MoviesList(
                dataResult = (result as ResultState.Success<TmdbResponse>).body!!,
                onPrevPage = { vm.getPopularMoviesPrev() },
                onNextPage = { vm.getPopularMoviesNext() }
            )
        }

        is ResultState.Error -> {}
        is ResultState.Exception -> {}
        is ResultState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}