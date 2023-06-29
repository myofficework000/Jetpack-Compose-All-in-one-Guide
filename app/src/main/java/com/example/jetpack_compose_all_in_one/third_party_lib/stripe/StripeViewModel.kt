package com.example.jetpack_compose_all_in_one.third_party_lib.stripe

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StripeViewModel @Inject constructor(
    private val repo: StripeRepositoryImpl
): ViewModel() {
    // This secret comes from the backend and will be cleared after launching the payment window
    var stripeClientSecret by mutableStateOf<String?>(null)
        private set

    // This is for the UI to process the result when they don't like doing it in the
    //      view model
    var stripePaymentState by mutableStateOf<PaymentSheetResult?>(null)
        private set

    // These are all measures to prevent users from launching multiple windows
    var isOnCooldown by mutableStateOf(false)
        private set
    private var cooldownJob: Job? = null
    var isPaying by mutableStateOf(false)
        private set

    // Requests client secret and auto-launches checkout activity upon receiving it
    fun initiatePayment() {
        clickCooldown()

        viewModelScope.launch(Dispatchers.IO) {
            with (repo.retrieveClientSecret()) {
                body()?.let {
                    stripeClientSecret = it.paymentIntent
                }
            }
        }
    }

    // Be a good boy and clean up ur mess.
    fun afterLaunch() {
        stripeClientSecret = null
        isPaying = true
        isOnCooldown = false
        cooldownJob = null
    }

    // The response handling after user interacted with the checkout activity.
    fun onPaymentResult(result: PaymentSheetResult) {
        stripePaymentState = result
        isPaying = false

        when(result) {
            PaymentSheetResult.Canceled -> {
                Log.i("Stripe Result", "Payment is cancelled")
            }
            PaymentSheetResult.Completed -> {
                Log.i("Stripe Result", "Payment has completed")
            }
            is PaymentSheetResult.Failed -> {
                Log.i("Stripe Result", "Error occurred")
            }
        }
    }

    // Notifies viewmodel that the UI doesn't need the payment state anymore
    fun paymentStateProcessed() {
        stripePaymentState = null
    }

    // This gives a cooldown to the button upon clicking.
    // For now it's either 10 seconds, or when the window is launched.
    private fun clickCooldown() {
        isOnCooldown = true
        cooldownJob = viewModelScope.launch {
            delay(10000)
            isOnCooldown = false
            cooldownJob = null
        }
    }
}