package com.example.jetpack_compose_all_in_one.history_of_day.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpack_compose_all_in_one.demos.history_of_day.model.Dates
import com.example.jetpack_compose_all_in_one.demos.history_of_day.repository.HistoryRepositoryImpl
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.ApiService
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.NetworkResult

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

class HistoryRepositoryImplMockitoTest{

  val testDispatcher =  StandardTestDispatcher()
  @get:Rule
  val executor = InstantTaskExecutorRule()

  @Mock
  lateinit var apiService: ApiService


  @Before
  fun setUp(){
    MockitoAnnotations.openMocks(this)
    Dispatchers.setMain(testDispatcher)
  }

  @Test
  fun `test fetch history response when empty received from server expect Network Error res`(){
    runTest {
      Mockito.`when`(apiService.getHistoryOfToday()).thenReturn(arrayListOf())
      val sut = HistoryRepositoryImpl(apiService);
      var res = sut.fetchHistory()
      assertEquals(NetworkResult.Error<List<Dates>>("Something went wrong").message, res.message)
    }
  }

  @Test
  fun `test fetch history response when null received from server expect Network Error res`(){
    runTest {
      Mockito.`when`(apiService.getHistoryOfToday()).thenReturn(null)
      val sut = HistoryRepositoryImpl(apiService);
      val res = sut.fetchHistory()
      assertEquals(NetworkResult.Error<List<Dates>>("Something went wrong").message, res.message)
    }
  }

  @Test
  fun `test fetch history when list received from server expect Network Success res`(){
    runTest {
      val dataResponse = arrayListOf<Dates>(
        Dates("1-November-1973", "This is dummy data")
      )
      Mockito.`when`(apiService.getHistoryOfToday()).thenReturn(dataResponse)
      val sut = HistoryRepositoryImpl(apiService);
      val res = sut.fetchHistory()
      val expected = NetworkResult.Success<List<Dates>>("Success", dataResponse)
      assertEquals(expected.message, res.message)
      assertEquals(expected.data?.size, 1)
    }
  }

  @After
  fun cleanUp(){
    Dispatchers.resetMain()
  }

}