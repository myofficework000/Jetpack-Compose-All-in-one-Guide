package com.example.jetpack_compose_all_in_one.history_of_day.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.jetpack_compose_all_in_one.demos.history_of_day.model.Dates
import com.example.jetpack_compose_all_in_one.demos.history_of_day.repository.HistoryRepository
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.NetworkResult
import com.technocraze.mvvmdatehistroy.viewmodel.HistoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class HistoryViewModelTest {

  @Mock
  lateinit var repository: HistoryRepository
  @get:Rule
  val instantTaskExecutorRule = InstantTaskExecutorRule()
  private val testDispatcher = StandardTestDispatcher()

  @Before
  fun setup() {
    MockitoAnnotations.openMocks(this)
    Dispatchers.setMain(testDispatcher)
  }

  @Test
  fun `get history data from api`() {
    runTest {
      val dataResponse = arrayListOf<Dates>(
        Dates("1-November-1973", "This is dummy data")
      )
      Mockito.`when`(repository.fetchHistory())
        .thenReturn(NetworkResult.Success<List<Dates>>("success", dataResponse))
      val viewModel = HistoryViewModel(repository)
      viewModel.getHistory()
      testDispatcher.scheduler.advanceUntilIdle()
      viewModel.historyState.test {
        var item = awaitItem()
        assertEquals(1, item.historyDates.size)
        cancel()
      }
    }
  }

  @Test
  fun `get history data that is null  expecting error`() {
    runTest {
      Mockito.`when`(repository.fetchHistory()).thenReturn(NetworkResult.Error("Something went wrong"))
      val viewModel = HistoryViewModel(repository)
      viewModel.getHistory()
      testDispatcher.scheduler.advanceUntilIdle()
      viewModel.historyState.test {
        var item = awaitItem()
        var expected = HistoryViewModel.HistoryState(
          isLoading = false,
          historyDates = emptyList(),
          errorMessage = "Something went wrong"
        )
        assertEquals(expected, item)
        cancel()
      }
    }
  }

  @Test
  fun `default state of loading when viewmodel initialized`() {
    runTest {
      val viewModel = HistoryViewModel(repository)
      viewModel.historyState.test {
        var state = awaitItem()
        assertEquals(true, state.isLoading)
        cancel()
      }
    }
  }

  @Test
  fun `default state of loading after data loaded`() {
    runTest {
      val dataResponse = arrayListOf<Dates>(
        Dates("1-November-1973", "This is dummy data")
      )
      Mockito.`when`(repository.fetchHistory())
        .thenReturn(NetworkResult.Success<List<Dates>>("success", dataResponse))
      val viewModel = HistoryViewModel(repository)
      viewModel.getHistory()
      testDispatcher.scheduler.advanceUntilIdle()
      viewModel.historyState.test {
        val state = awaitItem()
        var expected = HistoryViewModel.HistoryState(
          isLoading = false,
        )
        assertEquals(expected.isLoading, state.isLoading)
        cancel()
      }
    }
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }
}