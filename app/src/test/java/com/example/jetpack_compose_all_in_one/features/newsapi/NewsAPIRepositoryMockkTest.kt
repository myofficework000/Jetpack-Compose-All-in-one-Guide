package com.example.jetpack_compose_all_in_one.features.newsapi

import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.Article
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.HeadlineResponse
import com.example.jetpack_compose_all_in_one.features.newsapi.data.data.Source
import com.example.jetpack_compose_all_in_one.features.newsapi.data.remote.NewsApiService
import com.example.jetpack_compose_all_in_one.features.newsapi.repository.NewsApiRepositoryImpl
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsAPIRepositoryMockkTest {
    private val apiService = mockk<NewsApiService>(relaxed = true)
    lateinit var newsApiRepositoryImpl: NewsApiRepositoryImpl

    @Before
    fun setup(){
        newsApiRepositoryImpl = NewsApiRepositoryImpl(apiService)
    }

    @Test
    fun `Given news data is empty, When fetching news data, Then returns empty list `() = runTest {
        coEvery { apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY) } returns Response.success(
            HeadlineResponse(listOf(),"0",0)
        )
       val result = newsApiRepositoryImpl.getHeadLines()

        result.collect{
            if(it is ResultState.Success){
               assert(it.body?.status == "0")
               assert(it.body?.totalResults == 0)
               assert(it.body?.articles == emptyList<Article>())
            }
        }
    }

    @Test
    fun `Given news data is NOT empty, When fetching news data, Then returns NOT empty list `() = runTest {
        coEvery { apiService.getTopHeadlines(Constants.NEWS_API_COUNTRY, Constants.NEWS_API_KEY) } returns Response.success(
            HeadlineResponse(listOfArticle,"200",1)
        )
        val result = newsApiRepositoryImpl.getHeadLines()

        result.collect{
            if(it is ResultState.Success){
                assert(it.body?.status == "200")
                assert(it.body?.totalResults == 1)
                assert(it.body?.articles?.size == 1)
            }
        }
    }

    @Test
    fun `Given news data is empty, When fetching news data, Then returns error `() = runTest {
        val errorResponse = "Error!"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        val mockResponse = Response.error<HeadlineResponse>(400, errorResponseBody)
        coEvery {
            apiService.getTopHeadlines(
                Constants.NEWS_API_COUNTRY,
                Constants.NEWS_API_KEY
            )
        } returns mockResponse

        val result = newsApiRepositoryImpl.getHeadLines()

        result.collect {
            assert(it is ResultState.Error)
            if (it is ResultState.Error) {
                assert(it.errorMessage == "Error: 400")
            }
        }
    }

    companion object {
        val listOfArticle = listOf(
            Article(
                author = "A", content = "A", description = "A", publishedAt = "A",
                source = Source(id = "A", name = "A"), title = "A", url = "", urlToImage = ""
            )
        )
    }
}