package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.CountriesRepository

class ViewModelFactory(private val countriesRepository: CountriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContinentsViewModel::class.java)) {
            return ContinentsViewModel(countriesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
