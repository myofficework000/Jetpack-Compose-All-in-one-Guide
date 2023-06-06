package com.example.jetpack_compose_all_in_one.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxImpl
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxResponse
import com.example.jetpack_compose_all_in_one.features.random_fox.viewmodel.RandomFoxViewModel
import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Callable


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

        // Since view model runs all 3 fetches on init{}, a default result needs to be mocked here.
        val result = ResultState.Success( RandomFoxResponse("") )
        coEvery { repository.getRandomFoxCoroutines() } returns result
        coEvery { repository.getRandomFoxFlow() } returns flow { emit( result ) }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        every { repository.getRandomFoxRxJava() } returns Single.just(RandomFoxResponse(""))

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

    @Test
    fun `fetchRandomFoxUsingFlow updates foxStateFlow on success`() = runTest {
        coEvery { repository.getRandomFoxFlow() } returns flow {
            emit(
                ResultState.Success(
                    RandomFoxResponse("https://randomfox.ca/?i=69")
                )
            )
        }

        viewModel.fetchRandomFoxUsingFlow()

        coVerify { repository.getRandomFoxFlow() }
        assert(viewModel.foxStateFlow.value.image == "https://randomfox.ca/?i=69")
    }

    @Test
    fun `fetchRandomFoxUsingRxJava updates foxStateRxJava on success`() = runTest {
        coEvery { repository.getRandomFoxRxJava() } returns Single.just(
            RandomFoxResponse("https://randomfox.ca/?i=69")
        )

        viewModel.fetchRandomFoxUsingRxJava()

        verify { repository.getRandomFoxRxJava() }
        assert(viewModel.foxStateRxJava.value?.image == "https://randomfox.ca/?i=69")
    }
}


