package com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository

import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponse
import com.example.jetpack_compose_all_in_one.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getPopularMovies(page: Int): Flow<ResultState<TmdbResponse>>
}