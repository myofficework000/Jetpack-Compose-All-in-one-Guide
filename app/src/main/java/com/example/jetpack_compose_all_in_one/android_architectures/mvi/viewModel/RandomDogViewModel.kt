package com.example.jetpack_compose_all_in_one.android_architectures.mvi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.android_architectures.mvi.data.DogApiResponse
import com.example.jetpack_compose_all_in_one.android_architectures.mvi.repo.DogAPIRepo
import com.example.jetpack_compose_all_in_one.android_architectures.mvi.resource.Resource
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomDogViewModel @Inject constructor(private val dogAPIRepo: DogAPIRepo): ViewModel() {

    private val _viewState = MutableStateFlow<Resource<RandomDogResponse>>(Resource.Initial(RandomDogResponse("", "")))
    val viewState = _viewState

    private fun getRandomDog() {
        viewModelScope.launch (Dispatchers.IO){
            try {
                _viewState.value = Resource.Loading(_viewState.value.data)
                val response = dogAPIRepo.getDogInfo()
                response.body()?.let {
                    _viewState.value = Resource.Success(it)
                } ?: run {
                    _viewState.value = Resource.Failure(_viewState.value.data)
                }
            } catch (e: Exception){
                _viewState.value = Resource.Failure(_viewState.value.data)
            }
        }
    }

    fun triggerAction(action: Action) {
        when (action){
            Action.LoadRandomDog -> {
                getRandomDog()
            }
        }
    }

    sealed class Action {
        object LoadRandomDog: Action()
    }
}