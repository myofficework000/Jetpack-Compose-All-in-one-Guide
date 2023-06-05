package com.example.jetpack_compose_all_in_one.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxImpl
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxResponse
import com.example.jetpack_compose_all_in_one.features.random_fox.viewmodel.RandomFoxViewModel
import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RandomFoxViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = MainDispatcherRule()

    private lateinit var viewModel: RandomFoxViewModel
    private lateinit var repository: RandomFoxImpl

    @Before
    fun setup() {
        repository = mockk()
        viewModel = RandomFoxViewModel(repository)
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `fetchRandomFoxUsingCoroutines should update foxStateCoroutines on success`() = runTest {
        // Mock the response from the repository
        val foxResponse = RandomFoxResponse("https://example.com/image.jpg")
        coEvery { repository.getRandomFoxCoroutines() } returns ResultState.Success(foxResponse)

        // Create a mock observer for foxStateCoroutines
        val foxStateObserver = mockk<Observer<RandomFoxResponse>>(relaxed = true)
        viewModel.foxStateCoroutines.observeForever(foxStateObserver)

        // Call the method to be tested
        viewModel.fetchRandomFoxUsingCoroutines()

        // Verify that foxStateCoroutines was updated with the expected value
        verify { foxStateObserver.onChanged(foxResponse) }
    }

    @Test
    fun `fetchRandomFoxUsingCoroutines should update errorState on error`() = runTest {
        // Mock the error response from the repository
        val errorMessage = "Failed to fetch random fox"
        coEvery { repository.getRandomFoxCoroutines() } returns ResultState.Error(errorMessage)

        // Create a mock observer for errorState
        val errorStateObserver = mockk<Observer<String>>(relaxed = true)
        viewModel.errorState.observeForever(errorStateObserver)

        // Call the method to be tested
        viewModel.fetchRandomFoxUsingCoroutines()

        // Verify that errorState was updated with the expected value
        verify { errorStateObserver.onChanged(errorMessage) }
    }

    @Test
    fun `fetchRandomFoxUsingCoroutines should update loadingState when loading`() = runTest {
        // Mock the loading state from the repository
        coEvery { repository.getRandomFoxCoroutines() } returns ResultState.Loading()

        // Create a mock observer for loadingState
        val loadingStateObserver = mockk<Observer<Boolean>>(relaxed = true)
        viewModel.loadingState.observeForever(loadingStateObserver)

        // Call the method to be tested
        viewModel.fetchRandomFoxUsingCoroutines()

        // Verify that loadingState was updated with the expected value
        verify { loadingStateObserver.onChanged(true) }
    }

    @Test
    fun `fetchRandomFoxUsingCoroutines should update loadingState when not loading`() = runTest {
        // Mock a result other than Success, Error, or Loading
        coEvery { repository.getRandomFoxCoroutines() } returns mockk()

        // Create a mock observer for loadingState
        val loadingStateObserver = mockk<Observer<Boolean>>(relaxed = true)
        viewModel.loadingState.observeForever(loadingStateObserver)

        // Call the method to be tested
        viewModel.fetchRandomFoxUsingCoroutines()

        // Verify that loadingState was updated with the expected value
        verify { loadingStateObserver.onChanged(false) }
    }
}


