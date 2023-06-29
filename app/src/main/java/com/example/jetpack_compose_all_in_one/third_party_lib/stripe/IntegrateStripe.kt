package com.example.jetpack_compose_all_in_one.third_party_lib.stripe

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.utils.toReadable
import com.stripe.android.paymentsheet.PaymentSheetContract
import kotlin.reflect.KSuspendFunction2

// Test cards
// https://stripe.com/docs/testing

//Joshua
@Composable
fun IntegrateStripe(
    vm: StripeViewModel,
    snackbarFunc: KSuspendFunction2<String, Boolean, SnackbarResult>
) {
    // The launcher that launcher the checkout activity
    val stripeLauncher = rememberLauncherForActivityResult(
        contract = PaymentSheetContract(),
        onResult = { vm.onPaymentResult(it) }
    )

    val stripeBaseUrl = stringResource(R.string.STRIPE_BACKEND_BASEURL)
        .takeIf { it.contains("http") }

    // Basically tells the system to only launch the checkout activity when
    //      the client secret is received
    LaunchedEffect(vm.stripeClientSecret) {
        vm.stripeClientSecret?.let{
            stripeLauncher.launch(PaymentSheetContract.Args.createPaymentIntentArgs(it))
            vm.afterLaunch()
        }
    }

    LaunchedEffect(vm.stripePaymentState) {
        vm.stripePaymentState?.let {
            snackbarFunc(it.toReadable(), false)
        }
        vm.paymentStateProcessed()
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxWidth(0.7f)
                .background(
                    if (stripeBaseUrl == null) Color.Gray.copy(alpha = 0.3f)
                    else Color.Unspecified
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (stripeBaseUrl == null) {
                Text(
                    stringResource(R.string.stripe_no_url_error),
                    color = MaterialTheme.colorScheme.onError
                )
            }
            SimpleTextButton(
                "Pay me money",
                enabled = stripeBaseUrl != null && !vm.isOnCooldown && !vm.isPaying
            ) {
                vm.initiatePayment()
            }
        }

        if (vm.isOnCooldown) {
            Box(
                Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    Modifier.size(256.dp),
                    strokeWidth = 32.dp
                )
            }
        }
    }
}