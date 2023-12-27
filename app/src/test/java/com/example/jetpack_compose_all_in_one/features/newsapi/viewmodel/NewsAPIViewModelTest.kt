package com.example.jetpack_compose_all_in_one.features.newsapi.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.Article
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.features.newsapi.data.remote.NewsApiService
import com.example.jetpack_compose_all_in_one.features.newsapi.repository.NewsAPIRepositoryMockkTest
import com.example.jetpack_compose_all_in_one.features.newsapi.repository.NewsApiRepositoryImpl
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class NewsAPIViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    private val apiService = mockk<NewsApiService>(relaxed = true)
    private lateinit var newsApiRepositoryImpl: NewsApiRepositoryImpl
    private lateinit var viewModel: NewsApiViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        newsApiRepositoryImpl = NewsApiRepositoryImpl(apiService)
        viewModel = NewsApiViewModel(newsApiRepositoryImpl)
    }

    @Test
    fun `VM, Given news data is empty, When fetching news data, Then returns empty list `() = runTest {
        coEvery { apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY) } returns Response.success(
            HeadlineResponse(listOf(),"0",0)
        )
        viewModel.getHeadlinesNews()
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY) }
        val response = (viewModel.news.value as ResultState.Success).body
        response.let {
            assert(it?.status == "0")
            assert(it?.totalResults == 0)
            assert(it?.articles == emptyList<Article>())
        }
    }
    @Test
    fun `VM, Given news data is NOT empty, When fetching news data, Then returns NOT empty list `() = runTest {
        coEvery { apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY) } returns Response.success(
            HeadlineResponse(NewsAPIRepositoryMockkTest.listOfArticle,"200",1)
        )

        viewModel.getHeadlinesNews()
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY) }
        val response = (viewModel.news.value as ResultState.Success).body
        response.let {
                assert(it?.status == "200")
                assert(it?.totalResults == 1)
                assert(it?.articles?.size == 1)
        }
    }

    @Test
    fun `VM, Given news data is empty, When fetching news data, Then returns error `() = runTest {
        val errorResponse = "Error!"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        val mockResponse = Response.error<HeadlineResponse>(400, errorResponseBody)
        coEvery {
            apiService.getTopHeadlines(
                Constants.NEWS_API_COUNTRY,
                Constants.NEWS_API_KEY
            )
        } returns mockResponse

        viewModel.getHeadlinesNews()
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY) }
        val response = (viewModel.news.value as ResultState.Error)
        assert(response.errorMessage == "Error: 400")
    }
}