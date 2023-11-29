package com.example.jetpack_compose_all_in_one.viewmodel

import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.dto.CurrencyResponse
import com.example.jetpack_compose_all_in_one.demos.currency_converter.data.repository.CurrencyRepository
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyIntent
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.intent.CurrencyState
import com.example.jetpack_compose_all_in_one.demos.currency_converter.presentation.viewmodel.CurrencyViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CurrencyConverterViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<CurrencyRepository>(relaxed = true)
    private lateinit var viewModel: CurrencyViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CurrencyViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given user input, When fetching currency data, Then state is success and contains converted value`() = runTest {
        val expectedResponse = CurrencyResponse("2010-01-01", 130.0)
        coEvery { repository.getEURToJPY() } returns Result.success(expectedResponse)

        viewModel.handleIntent(CurrencyIntent.CurrencyInputChanged("100"))
        advanceUntilIdle()

        val actualState = viewModel.state.value
        val expectedState = CurrencyState.Success(expectedResponse)
        assertEquals(expectedState, actualState)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Given user input, When encountering network exception, Then state is Error`() = runTest {
        coEvery { repository.getEURToJPY() } returns Result.failure(RuntimeException("Network Error"))

        viewModel.handleIntent(CurrencyIntent.CurrencyInputChanged("100"))
        advanceUntilIdle()

        TestCase.assertTrue(viewModel.state.value is CurrencyState.Error)
    }
}