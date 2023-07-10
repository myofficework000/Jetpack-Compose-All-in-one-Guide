package com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.data.Business
import com.example.jetpack_compose_all_in_one.third_party_lib.yelp_api.repo.YelpRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YelpViewModel @Inject constructor(private val repo: YelpRepo): ViewModel() {

    private val _businessList = MutableStateFlow<List<Business>>(emptyList())
    val businessList = _businessList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _businessList.value = repo.getBusinesses().businessList
        }
    }

}