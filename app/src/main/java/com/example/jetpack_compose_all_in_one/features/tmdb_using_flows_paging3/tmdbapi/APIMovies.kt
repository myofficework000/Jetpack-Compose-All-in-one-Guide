package com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi

import com.example.jetpack_compose_all_in_one.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIMovies {
    @GET(Constants.tmdb_endpoint_popular)
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): Response<TmdbResponse>
}