package com.example.jetpack_compose_all_in_one.ui.views.tmdbapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponse
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponseItem
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.IMovieRepository
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.MovieRepository
import com.example.jetpack_compose_all_in_one.di.TMDBAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    @TMDBAPI val movieRepository: IMovieRepository,
    @TMDBAPI private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _popularMovies = MutableStateFlow(TmdbResponse.empty)
    val popularMovies = _popularMovies.asStateFlow()
    var totalPages: Int = 1
        private set
    var page: Int = 1
        private set

    fun getPopularMovies(page: Int) {
        viewModelScope.launch(ioDispatcher){
            movieRepository.getPopularMovies(page).collect{
                _popularMovies.value = it
                this@MovieViewModel.page = page
                this@MovieViewModel.totalPages = it.totalPages
            }
        }
    }

    fun getPopularMoviesNext() {
        if (page >= totalPages) return
        getPopularMovies(page+1)
    }
    fun getPopularMoviesPrev() {
        if (page < 2) return
        getPopularMovies(page-1)
    }
}