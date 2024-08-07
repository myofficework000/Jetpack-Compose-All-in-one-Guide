package com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.FindCountriesOfAContinentQuery
import com.example.jetpack_compose_all_in_one.GetContinentsQuery
import com.example.jetpack_compose_all_in_one.di.CountryAPI
import com.example.jetpack_compose_all_in_one.third_party_lib.graph_ql_country_list.model.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinentsViewModel @Inject constructor(
    @CountryAPI private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _continents = MutableLiveData<List<GetContinentsQuery.Continent>>()
    val continents: LiveData<List<GetContinentsQuery.Continent>> get() = _continents

    private val _countriesInContinent = MutableLiveData<List<FindCountriesOfAContinentQuery.Country>>()
    val countriesInContinent: LiveData<List<FindCountriesOfAContinentQuery.Country>> get() = _countriesInContinent

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        fetchContinents()
    }

    private fun fetchContinents() {
        viewModelScope.launch {
            val response = countriesRepository.getContinents()
            if (response.hasErrors() || response.data == null) {
                _error.value = response.errors?.firstOrNull()?.message ?: "Unknown error"
            } else {
                _continents.value = response.data!!.continents
            }
        }
    }

    fun fetchCountriesOfSelectedContinent(continentCode: String) {
        viewModelScope.launch {
            val response = countriesRepository.getCountriesOfSelectedContinent(continentCode)
            if (response.hasErrors() || response.data == null) {
                _error.value = response.errors?.firstOrNull()?.message ?: "Unknown error"
            } else {
                _countriesInContinent.value = response.data!!.continent?.countries
            }
        }
    }
}

