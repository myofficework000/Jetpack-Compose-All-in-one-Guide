package com.example.jetpack_compose_all_in_one.lessons.lesson_20

import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executors

@Composable
fun BiometricAuthentication() {
    val context = LocalContext.current

    val biometricManager = BiometricManager.from(context)
    when (biometricManager.canAuthenticate()) {
        BiometricManager.BIOMETRIC_SUCCESS ->
            Log.d("BiometricAuth", "App can authenticate using biometrics.")
        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
            Log.e("BiometricAuth", "No biometric features available on this device.")
        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
            Log.e("BiometricAuth", "Biometric features are currently unavailable.")
        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
            Log.e("BiometricAuth", "The user hasn't associated any biometric credentials with their account.")
    }

    val biometricPrompt: BiometricPrompt?

    if (context is FragmentActivity) {
        biometricPrompt = BiometricPrompt(context,
            Executors.newSingleThreadExecutor(),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context, "Authentication error: $errString", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Authentication failed. Try again.", Toast.LENGTH_SHORT).show()
                }
            }
        )
    } else {
        biometricPrompt = null
    }

    Button(onClick = {
        biometricPrompt?.let { prompt ->
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build()

            prompt.authenticate(promptInfo)
        }
    }) {
        Text("Authenticate")
    }
}
