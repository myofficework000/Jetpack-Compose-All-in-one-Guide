package com.example.jetpack_compose_all_in_one.demos.polls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor():ViewModel() {

    private var _attemptedTime = MutableLiveData(0)
    var attemptedTime:LiveData<Int> = _attemptedTime

    fun submitPoll() = _attemptedTime.postValue(_attemptedTime.value!!+1)
}