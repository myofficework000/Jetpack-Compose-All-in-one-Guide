package com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.model.AirtelResponse
import com.example.jetpack_compose_all_in_one.third_party_lib.airtel_api.repo.AirtelRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirtelViewModel @Inject constructor(private val repo: AirtelRepositoryImpl): ViewModel() {

    val result = MutableLiveData<AirtelResponse>()

    fun showLocationDetailsOnIp(ip_address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(repo.getLocationDetails(ip_address).body())
        }
    }

}