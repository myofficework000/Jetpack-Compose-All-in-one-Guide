package com.example.jetpack_compose_all_in_one.data.tmdbapi.repository

import com.example.jetpack_compose_all_in_one.data.tmdbapi.TmdbResponse
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovies(page: Int): Flow<TmdbResponse>
}