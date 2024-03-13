package com.example.jetpack_compose_all_in_one.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.jetpack_compose_all_in_one.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This module provides dependencies related to notifications, such as notification builder
 * and notification manager.
 */
@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    /**
     * Provides a NotificationCompat.Builder instance for building notifications.
     * @param context Application context.
     * @return NotificationCompat.Builder instance.
     */
    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "Main Channel ID")
            .setContentTitle("Inspiration for today")
            .setContentText("Life is hard")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    /**
     * Provides a NotificationManagerCompat instance for managing notifications.
     * Also creates a notification channel if it doesn't exist.
     * @param context Application context.
     * @return NotificationManagerCompat instance.
     */
    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannel(
            "Main Channel ID",
            "Main Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        return notificationManager
    }
}
