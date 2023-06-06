package com.example.jetpack_compose_all_in_one.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogRepository
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogResponse
import com.example.jetpack_compose_all_in_one.features.random_dog_api.viewmodel.RandomDogViewModel
import com.example.jetpack_compose_all_in_one.utils.Constants
import com.example.jetpack_compose_all_in_one.utils.ResultState
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class RandomDogViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<RandomDogRepository>()
    private val successObserver = mockk<Observer<RandomDogResponse>>(relaxed = true)
    private lateinit var viewModel: RandomDogViewModel

    @Before
    fun setUp() {
        val result = Response.success(RandomDogResponse("", ""))
        coEvery { repository.getRandomDogUsingCoroutines() } returns result
        coEvery { repository.getRandomDogUsingFlows()} returns flow { emit( result ) }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        every { repository.getRandomDogUsingRxJava() } returns Single.just(RandomDogResponse("",""))

        viewModel = RandomDogViewModel(repository)
    }

    @Test
    fun `calling random dog api receive success result`() {
        runTest {
            val responseFromAPI = Response.success(
                Gson().fromJson(
                    Constants.SUCCESS_RESULT_WITH_DATA,
                    RandomDogResponse::class.java
                )
            )

            coEvery { repository.getRandomDogUsingCoroutines() } returns responseFromAPI

            viewModel.apply {
                dogLiveDataCoroutines.observeForever(successObserver)
                getRandomDogUsingCoroutines()

                coVerify {
                    repository.getRandomDogUsingCoroutines()
                }

                val expectedResult =
                    Gson().fromJson(Constants.SUCCESS_RESULT_WITH_DATA, RandomDogResponse::class.java)

                coVerify { successObserver.onChanged(expectedResult) }
            }
        }
    }
}