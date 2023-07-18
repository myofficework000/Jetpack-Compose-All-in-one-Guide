package com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.GetLaunchesPastQuery
import com.example.jetpack_compose_all_in_one.di.SpaceXAPI
import com.example.jetpack_compose_all_in_one.third_party_lib.spacex_api_graphql.remote.SpaceXLaunchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceXLaunchesViewModel @Inject constructor(
    @SpaceXAPI private val repository: SpaceXLaunchesRepository,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    init{ getPastLaunches() }

    var launches by mutableStateOf(listOf<GetLaunchesPastQuery.LaunchesPast>())
        private set

    private fun getPastLaunches() {
        viewModelScope.launch(ioDispatcher) {
            repository.getPastLaunches().apply {
                data?.launchesPast?.let {
                    launches = it
                }
            }
        }
    }
}