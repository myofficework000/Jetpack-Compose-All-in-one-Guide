package com.example.jetpack_compose_all_in_one.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.jetpack_compose_all_in_one.R
import com.google.android.libraries.places.api.Places
import com.stripe.android.PaymentConfiguration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp: Application() {
    companion object{
        lateinit var notificationManager: NotificationManager
    }

    override fun onCreate() {
        super.onCreate()

        // App is always above API 26
        val notificationChannel = NotificationChannel(
            "channel_id",
            "channel_name",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description = "notification channel desc.."
        notificationChannel.enableVibration(true)
        notificationChannel.enableLights(true)
        notificationChannel.vibrationPattern = longArrayOf(100,200,300,400,300,200,100)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        Places.initialize(applicationContext, resources.getString(R.string.GOOGLE_MAPS_API_KEY))
        PaymentConfiguration.init(applicationContext, resources.getString(R.string.STRIPE_API_KEY))
    }
}