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

/**
 * This class provides unit tests for the [NewsViewModel] class.
 * It verifies the behavior of the ViewModel under various conditions
 * such as fetching latest news and searching news.
 */
class NewsViewModelTest {

    // JUnit rule to instantly execute tasks on the main thread for LiveData testing
    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    // Mocked NewsRepository for testing
    private val repo = mockk<NewsRepository>()

    // The ViewModel under test
    private val vm = NewsViewModel(repo)

    /**
     * Setup method to configure RxJava scheduler for testing.
     * It ensures tasks are executed on the same thread.
     */
    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    /**
     * Unit test for verifying the behavior when fetching latest news with an empty response.
     * It checks if the [latestNewsResponse] LiveData is empty.
     */
    @Test
    fun `getLatestNews WHEN empty response THEN latestNewsResponse is empty`() {
        every { repo.getLatestNews() } returns Single.just(emptyResponse)

        vm.getLatestNews()

        verify { repo.getLatestNews() }
        assertTrue(vm.latestNewsResponse.value == emptyResponse)
    }

    /**
     * Unit test for verifying the behavior when fetching latest news with one response.
     * It checks if the [latestNewsResponse] LiveData contains one news item.
     */
    @Test
    fun `getLatestNews WHEN one response THEN latestNewsResponse has one item`() {
        every { repo.getLatestNews() } returns Single.just(oneResponse)

        vm.getLatestNews()

        verify { repo.getLatestNews() }
        assertTrue(vm.latestNewsResponse.value == oneResponse)
    }

    /**
     * Unit test for verifying the behavior when searching news with an empty response.
     * It checks if the [latestNewsResponse] LiveData is empty after searching.
     */
    @Test
    fun `searchNews WHEN empty response THEN latestNewsResponse is empty`() {
        every {
            repo.searchNews(FAKE_KEYWORD, FAKE_START_DATE, FAKE_END_DATE)
        } returns Single.just(emptyResponse)

        vm.searchNews(FAKE_KEYWORD, 0L, 0L)

        verify { repo.searchNews(FAKE_KEYWORD, FAKE_START_DATE, FAKE_END_DATE) }
        assertTrue(vm.latestNewsResponse.value == emptyResponse)
    }

    /**
     * Unit test for verifying the behavior when searching news with one response.
     * It checks if the [latestNewsResponse] LiveData contains one news item after searching.
     */
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
        // Fake response with one news item
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

        // Fake response with no news item
        val emptyResponse = LatestNewsResponse(
            news = listOf(),
            page = 0,
            status = "ok"
        )

        // Fake values for keyword and dates used in testing
        const val FAKE_KEYWORD = ""
        val FAKE_START_DATE = 0L.toNewsDate()
        val FAKE_END_DATE = 0L.toNewsDate()
    }
}
