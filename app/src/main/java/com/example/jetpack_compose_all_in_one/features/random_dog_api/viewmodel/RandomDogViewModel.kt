package com.example.jetpack_compose_all_in_one.features.random_dog_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogRepository
import com.example.jetpack_compose_all_in_one.features.random_dog_api.model.RandomDogResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomDogViewModel @Inject constructor(private val repository: RandomDogRepository) :
    ViewModel() {

    private val _dogLiveDataCoroutines = MutableLiveData<RandomDogResponse>()
    val dogLiveDataCoroutines: LiveData<RandomDogResponse> = _dogLiveDataCoroutines

    private val _dogLiveDataRxJava = MutableLiveData<RandomDogResponse>()
    val dogLiveDataRxJava: LiveData<RandomDogResponse> = _dogLiveDataRxJava


    private val _dogFlow = MutableStateFlow(RandomDogResponse("", ""))
    val dogFlow = _dogFlow.asStateFlow()

    private val error = MutableLiveData<String>()
    private lateinit var job: Job
    private lateinit var disposable: Disposable

    init {
        getRandomDogUsingCoroutines()
        getRandomDogUsingRxJava()
        getRandomDogUsingFlows()
    }

    fun getRandomDogUsingCoroutines() {
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getRandomDogUsingCoroutines()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _dogLiveDataCoroutines.postValue(it)
                        //dogFluff = it
                    }
                } else {
                    error.postValue("Failed to load data!")
                }
            } catch (e: Exception) {
                error.postValue(e.toString())
                e.printStackTrace()
            }
        }
    }

    fun getRandomDogUsingRxJava() {
        disposable = repository.getRandomDogUsingRxJava()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _dogLiveDataRxJava.postValue(it) },
                {
                    error.postValue("Failed to load data!")
                }
            )
    }

    fun getRandomDogUsingFlows() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRandomDogUsingFlows().collect {
                if (it.isSuccessful) {
                    it.body()?.let { response ->
                        _dogFlow.value = response
                    }
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) {
            job.cancel()
        }

        if (::disposable.isInitialized) {
            disposable.dispose()
        }
    }
}