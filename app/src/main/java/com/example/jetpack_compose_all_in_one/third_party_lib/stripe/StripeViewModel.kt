package com.example.jetpack_compose_all_in_one.third_party_lib.stripe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StripeViewModel @Inject constructor(
    private val repo: StripeRepository
): ViewModel() {
    var stripeClientSecret by mutableStateOf<String?>(null)
        private set

    // Requests client secret and auto-launches checkout activity upon receiving it
    fun initiatePayment() {
        with (repo.createPaymentIntent()) {
            stripeClientSecret = clientSecret
        }
    }

    // Be a good boy and clean up ur mess.
    fun clearClientSecret() {
        stripeClientSecret = null
    }

    // The response handling after user interacted with the checkout activity.
    fun onPaymentResult(result: PaymentSheetResult) {
        when(result) {
            PaymentSheetResult.Canceled -> {}
            PaymentSheetResult.Completed -> {}
            is PaymentSheetResult.Failed -> {}
        }
    }
}