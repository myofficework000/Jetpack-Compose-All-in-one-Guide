package com.example.jetpack_compose_all_in_one.third_party_lib.rxjava

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RxJavaDemoViewModel @Inject constructor(): ViewModel() {
    // For Completable
    var completableProgress by mutableStateOf<Int?>(null)
    // Null: Progress, False: Complete, True: Error
    var completableIsError by mutableStateOf<Boolean?>(false)
    var completableCompletes by mutableIntStateOf(0)
    var completableErrors by mutableIntStateOf(0)


    // For Single


    var wiFiIsOn by mutableStateOf(false)
    private val disposables = CompositeDisposable()

    fun completableEmit() {
        completableProgress = 0
        completableCompletes = 0
        completableErrors = 0
        completableIsError = null
        disposables.add(
            Completable.fromObservable(
                Observable.range(1,10)
                    .zipWith(Observable.interval(500, TimeUnit.MILLISECONDS)) { _,_ ->
                        completableProgress = completableProgress?.plus(1)
                        if (!wiFiIsOn) throw Exception("\"I wonder what this button does...\" Famous last words.")
                    }
            ).subscribe(
                { completableIsError = false; completableCompletes++ }, // Complete
                { completableIsError = true; completableErrors++ } // Error
            )
        )
    }

    fun completableToggleWiFi(isOn: Boolean) { wiFiIsOn = isOn }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}