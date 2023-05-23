package com.example.jetpack_compose_all_in_one.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpack_compose_all_in_one.features.domain_search.DomainSearchItem
import com.example.jetpack_compose_all_in_one.features.domain_search.DomainSearchRepository
import com.example.jetpack_compose_all_in_one.features.domain_search.DomainSearchResponse
import com.example.jetpack_compose_all_in_one.features.domain_search.DomainSearchViewModel
import com.example.jetpack_compose_all_in_one.utils.ResultState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*

import org.junit.Rule
import org.junit.Test

class DomainSearchViewModelTest {

    @JvmField
    @Rule
    val rule = InstantTaskExecutorRule()

    private val repo = mockk<DomainSearchRepository>()
    private val vm = DomainSearchViewModel(
        repo,
        Dispatchers.IO
    )

    @Test
    fun `WHEN empty response THEN searchResult is empty`() {
        coEvery { repo.searchDomain(FAKE_INPUT) } returns ResultState.Success(emptyResponse)

        vm.searchDomain(FAKE_INPUT)

        coVerify { repo.searchDomain(FAKE_INPUT) }

        val result = vm.searchResult.value as ResultState.Success
        assertTrue(result.body == emptyResponse)
    }

    @Test
    fun `WHEN one response THEN searchResult is has oneResponse`() {
        coEvery { repo.searchDomain(FAKE_INPUT) } returns ResultState.Success(oneResponse)

        vm.searchDomain(FAKE_INPUT)

        coVerify { repo.searchDomain(FAKE_INPUT) }

        val result = (vm.searchResult.value as ResultState.Success)
        assertTrue(result.body!! == oneResponse)
        assertTrue(result.body!!.domains.size == 1)
        assertTrue(result.body!!.domains.first().domain == "info-apple-cider-vinegar.site")
    }

    companion object {
        const val FAKE_INPUT = "xxx"

        val emptyResponse = DomainSearchResponse(
            domains = listOf(),
            time = "",
            total = 0
        )

        val oneResponse = DomainSearchResponse(
            domains = listOf(
                DomainSearchItem(
                    country = null,
                    createDate = "2023-05-20T11:55:30.592086",
                    domain = "info-apple-cider-vinegar.site",
                    isDead = "False",
                    updateDate = "2023-05-20T11:55:30.592089"
                )
            ),
            time = "365",
            total = 1
        )
    }
}