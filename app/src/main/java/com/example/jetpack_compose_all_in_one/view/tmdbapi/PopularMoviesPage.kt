package com.example.jetpack_compose_all_in_one.view.tmdbapi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.jetpack_compose_all_in_one.viewmodel.MovieViewModel

@Composable
fun PopularMoviesPage(
    vm: MovieViewModel
) {
    val result by vm.popularMovies.collectAsState()

    LaunchedEffect(Unit) {
        vm.getPopularMovies(1)
    }

    MoviesList(
        dataResult = result,
        onPrevPage = { vm.getPopularMoviesPrev() },
        onNextPage = { vm.getPopularMoviesNext() }
    )
}