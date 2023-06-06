package com.example.jetpack_compose_all_in_one

import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxImpl
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxResponse
import com.example.jetpack_compose_all_in_one.features.random_fox.model.RandomFoxService
import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.core.ValueClassSupport.boxedValue
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RandomFoxImplTest{

    private lateinit var randomFoxImpl: RandomFoxImpl
    private lateinit var service: RandomFoxService
    @Before
    fun setup() {
        service = mockk()
        randomFoxImpl = RandomFoxImpl(service)
    }

    @Test
    fun `getRandomFoxCoroutines should return Success state when API call is successful`() = runBlocking {
        // Given
        val response = RandomFoxResponse("https://randomfox.ca/images/41.jpg", "https://randomfox.ca/?i=41")
        coEvery { service.getRandomFoxUsingCoroutines() } returns Response.success(response)

        // When
        val resultState = randomFoxImpl.getRandomFoxCoroutines()

        // Then
        assert(resultState is ResultState.Success)
    }

}
