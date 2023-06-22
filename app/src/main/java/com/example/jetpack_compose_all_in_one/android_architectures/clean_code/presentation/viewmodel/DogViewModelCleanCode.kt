package com.example.jetpack_compose_all_in_one.android_architectures.clean_code.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogEntityCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.data.dto.DogResponseCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.repositories.DogRepositoryCleanCode
import com.example.jetpack_compose_all_in_one.android_architectures.clean_code.domain.usecases.GetDogUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModelCleanCode @Inject constructor(
    private val getRandomDogUsecase: GetDogUsecase
): ViewModel() {
    init { getRandomDog() }

    var dogResponse by mutableStateOf<DogEntityCleanCode?>(null)
        private set

    fun getRandomDog() {
        viewModelScope.launch(Dispatchers.IO) {
            dogResponse = getRandomDogUsecase()
        }
    }
}