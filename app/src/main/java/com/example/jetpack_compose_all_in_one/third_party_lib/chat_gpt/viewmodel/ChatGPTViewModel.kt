package com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.NetworkResult
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.remote.repository.ChatGPTRemoteRepository
import com.example.openai_app.model.remote.responsemodel.ChatResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGPTViewModel @Inject constructor(private val repository: ChatGPTRemoteRepository) : ViewModel() {
    private val _result = MutableLiveData<NetworkResult<ChatResponse>>(NetworkResult.Loading())
    val result: LiveData<NetworkResult<ChatResponse>>
        get() = _result

    fun sendMessage(mess: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.sendMessage(mess)
                .collect{
                    _result.postValue(it)
                }
        }
    }
}