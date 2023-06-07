package com.example.jetpack_compose_all_in_one.features.random_dog_api.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpack_compose_all_in_one.viewmodel.MainDispatcherRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.Single
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class RandomDogRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = MainDispatcherRule()

    private lateinit var service: RandomDogApiService
    private lateinit var repository: RandomDogRepository

    @Before
    fun setup(){
        service = mockk()
        repository = RandomDogRepository(service)
    }

    @After
    fun teardown(){
        clearAllMocks()
    }

    @Test
    fun `calling getRandomDogUsingRxJava function should give success`() = runTest{

        val dogResponse = RandomDogResponse("https://images.dog.ceo/breeds/cockapoo/george-tongue.jpg", "success")

        val response = Single.just(dogResponse)

        coEvery { service.getRandomDogUsingRxJava() } returns response

        val resultState = repository.getRandomDogUsingRxJava()

        assert(resultState == response)
    }

    @Test
    fun `calling getRandomDogUsingCoroutines function should give success` () = runTest {

        val dogResponse = RandomDogResponse("https://images.dog.ceo/breeds/cockapoo/george-tongue.jpg", "success")

        val response = Response.success(dogResponse)

        coEvery { service.getRandomDogUsingCoroutines() } returns response

        val resultState = repository.getRandomDogUsingCoroutines()

        assert(resultState == response)
    }
}