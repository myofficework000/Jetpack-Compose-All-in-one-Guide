package com.example.jetpack_compose_all_in_one.lessons.lesson_19

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val dogApiService: DogApiService) : ViewModel() {

    val dogImage = MutableLiveData<String>()

    init {
        getRandomDogImage()
    }

    fun getRandomDogImage() {
        viewModelScope.launch {
            try {
                val response = dogApiService.getDogImage()
                if (response.status == "success") {
                    dogImage.value = response.message
                } else {
                    Log.d("TAG", "getRandomDogImage: ${response.status}")
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}