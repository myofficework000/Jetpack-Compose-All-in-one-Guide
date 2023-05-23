package com.example.jetpack_compose_all_in_one.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.LatestNewsResponse
import com.example.jetpack_compose_all_in_one.features.news_sample.data.data.News
import com.example.jetpack_compose_all_in_one.features.news_sample.repository.NewsRepository
import com.example.jetpack_compose_all_in_one.ui.views.news_ui.list.NewsViewModel
import com.example.jetpack_compose_all_in_one.utils.toNewsDate
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    private val repo = mockk<NewsRepository>()
    private val vm = NewsViewModel(repo)

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `getLatestNews WHEN empty response THEN latestNewsResponse is empty`() {
        every { repo.getLatestNews() } returns Single.just(emptyResponse)

        vm.getLatestNews()

        verify { repo.getLatestNews() }
        assertTrue(vm.latestNewsResponse.value == emptyResponse)
    }

    @Test
    fun `getLatestNews WHEN one response THEN latestNewsResponse has one item`() {
        every { repo.getLatestNews() } returns Single.just(oneResponse)

        vm.getLatestNews()

        verify { repo.getLatestNews() }
        assertTrue(vm.latestNewsResponse.value == oneResponse)
    }

    @Test
    fun `searchNews WHEN empty response THEN latestNewsResponse is empty`() {
        every {
            repo.searchNews(FAKE_KEYWORD, FAKE_START_DATE, FAKE_END_DATE)
        } returns Single.just(emptyResponse)

        vm.searchNews(FAKE_KEYWORD, 0L, 0L)

        verify { repo.searchNews(FAKE_KEYWORD, FAKE_START_DATE, FAKE_END_DATE) }
        assertTrue(vm.latestNewsResponse.value == emptyResponse)
    }

    @Test
    fun `searchNews WHEN one response THEN latestNewsResponse has one item`() {
        every {
            repo.searchNews(FAKE_KEYWORD, FAKE_START_DATE, FAKE_END_DATE)
        } returns Single.just(oneResponse)

        vm.searchNews(FAKE_KEYWORD, 0L, 0L)

        verify { repo.searchNews(FAKE_KEYWORD, FAKE_START_DATE, FAKE_END_DATE) }
        assertTrue(vm.latestNewsResponse.value == oneResponse)
    }

    companion object {
        val oneResponse = LatestNewsResponse(
            news = listOf(
                News(
                    "",
                    listOf(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "Scientists have discovered Ligma",
                    ""
                )
            ),
            page = 1,
            status = "ok"
        )

        val emptyResponse = LatestNewsResponse(
            news = listOf(),
            page = 0,
            status = "ok"
        )

        const val FAKE_KEYWORD = ""
        val FAKE_START_DATE = 0L.toNewsDate()
        val FAKE_END_DATE = 0L.toNewsDate()
    }
}