package com.example.jetpack_compose_all_in_one.third_party_lib.stripe

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.stripe.android.paymentsheet.PaymentSheetContract

//Joshua
@Composable
fun IntegrateStripe(
    vm: StripeViewModel
) {
    // The launcher that launcher the checkout activity
    val stripeLauncher = rememberLauncherForActivityResult(
        contract = PaymentSheetContract(),
        onResult = { vm.onPaymentResult(it) }
    )

    // Basically tells the system to only launch the checkout activity when
    //      the client secret is received
    LaunchedEffect(vm.stripeClientSecret) {
        vm.stripeClientSecret?.let{
            stripeLauncher.launch(PaymentSheetContract.Args.createPaymentIntentArgs(it))
            vm.clearClientSecret()
        }
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SimpleTextButton("Pay me money") { vm.initiatePayment() }
    }
}