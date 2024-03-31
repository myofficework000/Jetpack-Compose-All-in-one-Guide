package com.example.jetpack_compose_all_in_one.firebase.crashanalytics

import androidx.compose.runtime.Composable
import com.google.firebase.crashlytics.FirebaseCrashlytics

@Composable
fun CrashAnalyticsDemo() {
    // Simulate a crash for demonstration purposes
    simulateCrash()

    // Log a message to Crashlytics
    FirebaseCrashlytics.getInstance().log("CrashAnalyticsDemo() called")

    // You can log additional information
    FirebaseCrashlytics.getInstance().setCustomKey("priority", "high")

    // Report non-fatal exceptions
    val exception = Exception("Non-fatal exception occurred")
    FirebaseCrashlytics.getInstance().recordException(exception)

    // You may also use setUserId() to associate users with crashes

    // Your UI Composables can go here
}

private fun simulateCrash() {
    // Simulate a crash
    throw RuntimeException("This is a simulated crash")
}
