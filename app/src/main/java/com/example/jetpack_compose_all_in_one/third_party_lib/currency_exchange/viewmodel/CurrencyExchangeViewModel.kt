package com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.third_party_lib.CurrencyInfo_exchange.remote.response.CurrencyResponse
import com.example.jetpack_compose_all_in_one.third_party_lib.currency_exchange.remote.repository.CurrencyRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyExchangeViewModel @Inject constructor(private val repository: CurrencyRepositoryImpl) :
    ViewModel() {
    init {
        getCurrencyData()
    }

    val currencyResponseMutableLiveData = MutableLiveData<CurrencyResponse>()
    val message = MutableLiveData<String>()

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        message.postValue(throwable.message)
    }

    fun getCurrencyData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCurrencies()

            if (!response.isSuccessful){
                message.postValue("Something Went Wrong!!")
                return@launch
            }
            else if(response.body()==null){
                message.postValue("Data is Empty")
                return@launch
            }
            currencyResponseMutableLiveData.postValue(response.body())
            message.postValue("Success")
            return@launch
        }
    }

}