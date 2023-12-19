package com.example.jetpack_compose_all_in_one.history_of_day.repository

import com.example.jetpack_compose_all_in_one.demos.history_of_day.model.Dates
import com.example.jetpack_compose_all_in_one.demos.history_of_day.repository.HistoryRepositoryImpl
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HistoryRepositoryImplMockk {

    private lateinit var subject: HistoryRepositoryImpl

    private val apiService = mockk<ApiService>(relaxed = true)

    @Before
    fun setUp() {
        subject = HistoryRepositoryImpl(apiService)
    }

    @Test
    fun fetchHistory_resultIsNull_returnNetworkError() = runTest {
        coEvery { apiService.getHistoryOfToday() } returns null

        val result = subject.fetchHistory()

        assert(result.message == "Something went wrong")
    }

    @Test
    fun fetchHistory_resultIsEmpty_returnNetworkError() = runTest {
        coEvery { apiService.getHistoryOfToday() } returns emptyList()

        val result = subject.fetchHistory()

        assert(result.message == "Something went wrong")
    }

    @Test
    fun fetchHistory_resultIsNotNullOrEmpty_returnSuccessResponse() = runTest {
        val response = listOf(Dates("2023-12-18", "date"))

        coEvery { apiService.getHistoryOfToday() } returns response

        val result = subject.fetchHistory()

        assert(result.message == "Success")
        assert(result.data == response)
    }
}