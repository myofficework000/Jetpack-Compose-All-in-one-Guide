package com.example.jetpack_compose_all_in_one.viewmodel

import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponse
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.MovieRepository
import com.example.jetpack_compose_all_in_one.ui.views.tmdbapi.MovieViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private val vm = mockk<MovieViewModel>()
    private val repo = mockk<MovieRepository>()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Get popular movies Returns 0 items`() = runTest{
        every { repo.getPopularMovies(1) } returns flow {
            emit(
                TmdbResponse(
                    page = 1,
                    results = listOf(),
                    totalPages = 1,
                    totalResults = 0
                )
            )
        }

        //*val result = repo.getPopularMovies(1).first()

        assertTrue{ repo.getPopularMovies(1).first() == }*//*
    }
}