package com.example.jetpack_compose_all_in_one.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.utils.Constants.STRIPE_PUBLISHABLE_KEY
import com.google.android.libraries.places.api.Places
import com.stripe.android.PaymentConfiguration
import dagger.hilt.android.HiltAndroidApp

/**
 * Main application class for the Android application.
 */
@HiltAndroidApp
class MainApp : Application() {
    companion object {
        /**
         * Notification manager instance.
         */
        lateinit var notificationManager: NotificationManager
    }

    override fun onCreate() {
        super.onCreate()

        // Create notification channel
        val notificationChannel = NotificationChannel(
            "channel_id",
            "channel_name",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description = "Notification channel description"
        notificationChannel.enableVibration(true)
        notificationChannel.enableLights(true)
        notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 300, 200, 100)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        // Initialize Google Places SDK
        Places.initialize(applicationContext, resources.getString(R.string.GOOGLE_MAPS_API_KEY))

        // Initialize Stripe SDK
        PaymentConfiguration.init(applicationContext, STRIPE_PUBLISHABLE_KEY)
    }
}
