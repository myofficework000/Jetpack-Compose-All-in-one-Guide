package com.example.jetpack_compose_all_in_one.demos.github_api.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.demos.github_api.data.model.GithubUser
import com.example.jetpack_compose_all_in_one.demos.github_api.domain.GetGithubUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserViewModel @Inject constructor(private val getGithubUserUseCase: GetGithubUserUseCase): ViewModel() {
    private val _users = MutableStateFlow<List<GithubUser>>(emptyList())
    val users: StateFlow<List<GithubUser>> = _users

    init {
        fetchGithubUsers(51234842)
    }

    private fun fetchGithubUsers(since: Int) {
        viewModelScope.launch {
            try {
                _users.value = getGithubUserUseCase(since)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}