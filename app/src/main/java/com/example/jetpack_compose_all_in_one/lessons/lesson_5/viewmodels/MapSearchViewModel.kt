package com.example.jetpack_compose_all_in_one.lessons.lesson_5.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.lessons.lesson_5.PlacesSearchItem
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapSearchViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val application: Application
): ViewModel() {
    var searchText by mutableStateOf("")
    var searchJob: Job? = null
    val searchResults = mutableStateListOf<PlacesSearchItem>()

    private val placesClient by lazy{ Places.createClient(application.applicationContext) }

    // triggerThres means the search will not run if the size is less than that.
    //      it'll also clear the result if under.
    fun searchPlace(input: String, triggerThres: Int = 3) {
        if (input.length < triggerThres) return

        searchJob?.cancel()
        searchJob = viewModelScope.launch(ioDispatcher) {
            delay(2000)
            val req = FindAutocompletePredictionsRequest.builder()
                .setQuery(input)
                .build()
            placesClient.findAutocompletePredictions(req)
                .addOnSuccessListener { result ->
                    searchResults.addAll(result.autocompletePredictions.map{
                        PlacesSearchItem(
                            it.placeId,
                            it.getFullText(null).toString()
                        )
                    })
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    println(it.cause)
                    println(it.message)
                }
        }
    }
}