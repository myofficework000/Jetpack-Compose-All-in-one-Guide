package com.example.jetpack_compose_all_in_one.viewmodel

import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponse
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.TmdbResponseItem
import com.example.jetpack_compose_all_in_one.features.tmdb_using_flows_paging3.tmdbapi.repository.MovieRepository
import com.example.jetpack_compose_all_in_one.ui.views.tmdbapi.MovieViewModel
import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private val repo = mockk<MovieRepository>()
    private lateinit var vm: MovieViewModel
    private val ioDispatcher = Dispatchers.IO

    @Before
    fun setUp() {
        vm = MovieViewModel(repo,ioDispatcher)
    }


    @Test
    fun `GIVEN emptyResponse WHEN getPopularMovies(1) THEN invoke sequence`() = runTest{
        coEvery { repo.getPopularMovies(1) } returns flow{
            emit(ResultState.Success(emptyResponse))
        }

        vm.getPopularMovies(1)

        coVerify { repo.getPopularMovies(1) }
        assert(vm.popularMovies.first() is ResultState.Success)

        val response = (vm.popularMovies.first() as ResultState.Success).body
        assert(response == emptyResponse)
    }

    @Test
    fun `GIVEN page2Response WHEN getPopularMovies(2) THEN invoke sequences`() = runTest{
        coEvery { repo.getPopularMovies(2) } returns flow{
            emit(ResultState.Success(page2Response))
        }

        vm.getPopularMovies(2)

        coVerify { repo.getPopularMovies(2) }
        assert(vm.popularMovies.first() is ResultState.Success)

        val response = (vm.popularMovies.first() as ResultState.Success).body
        assert(response!!.totalResults == 3)
        assert(response.results.count() == 2)
        assert(response.results.first().title == "answer")
    }

    // runTest nullifies all delay(), and since this test does require a small delay
    //      to load data, runBlocking is used instead
    @Test
    fun `GIVEN page2Response WHEN getPopularMoviesNext() THEN gets page 2`() = runBlocking{
        coEvery { repo.getPopularMovies(1) } returns flow{
            emit(ResultState.Success(page1Response))
        }
        coEvery { repo.getPopularMovies(2) } returns flow{
            emit(ResultState.Success(page2Response))
        }

        vm.getPopularMovies(1) // This does run after the page loaded.
        // Delay is required or the totalPages will not be updated fast enough
        //      for getPopularMoviesNext()
        delay(TEST_DELAY)
        vm.getPopularMoviesNext()
        delay(TEST_DELAY)

        coVerify { repo.getPopularMovies(2) }
        assert(vm.popularMovies.value is ResultState.Success)

        val response = (vm.popularMovies.value as ResultState.Success).body
        assert(vm.page == 2)
        assert(response!!.totalResults == 3)
        assert(response.results.count() == 2)
        assert(response.results.last().title == "question")
    }

    @Test
    fun `GIVEN page1Response WHEN getPopularMoviesPrev() THEN goes back to page 1`() = runBlocking{
        coEvery { repo.getPopularMovies(1) } returns flow{
            emit(ResultState.Success(page1Response))
        }
        // Even if page 2 is not checked, the system still needs to know
        //      the result. Otherwise it'll crash.
        coEvery { repo.getPopularMovies(2) } returns flow{
            emit(ResultState.Success(page2Response))
        }

        vm.getPopularMovies(1)
        delay(TEST_DELAY)
        vm.getPopularMoviesNext() // This is to move to page 2 first
        delay(TEST_DELAY)
        vm.getPopularMoviesPrev()
        delay(TEST_DELAY)

        coVerify { repo.getPopularMovies(1) }
        assert(vm.popularMovies.value is ResultState.Success)

        val response = (vm.popularMovies.value as ResultState.Success).body
        assert(vm.page == 1)
        assert(response!!.totalResults == 3)
        assert(response.results.count() == 1)
        assert(response.results.last().id == 69)
    }

    private companion object {
        val emptyResponse = TmdbResponse(
            1,
            listOf(),
            1,
            0
        )

        val page1Response = TmdbResponse(
            1,
            listOf(
                TmdbResponseItem.empty.copy(
                    id = 69
                )
            ),
            2,
            3
        )

        val page2Response = TmdbResponse(
            2,
            listOf(
                TmdbResponseItem.empty.copy(
                    id = 42,
                    title = "answer"
                ),
                TmdbResponseItem.empty.copy(
                    title = "question"
                )
            ),
            2,
            3
        )

        val mediaType: MediaType = MediaType.get("application/json; charset=utf-8")
        const val TEST_DELAY = 10L
    }
}