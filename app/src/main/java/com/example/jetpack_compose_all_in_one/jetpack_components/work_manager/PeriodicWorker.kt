package com.example.jetpack_compose_all_in_one.jetpack_components.work_manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpack_compose_all_in_one.R
import java.time.LocalTime
import java.util.concurrent.TimeUnit

class PeriodicWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    private val notifManager: NotificationManager
    private val cancelWorkIntent: PendingIntent

    init {
        (applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager).apply {
            createNotificationChannel(
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = channelDesc
                }
            )

            notifManager = this
        }

        cancelWorkIntent = WorkManager.getInstance(appContext).createCancelPendingIntent(id)
    }

    override fun doWork(): Result {
        val hour = LocalTime.now().hour
        // turns 0 O'clock to 12 O'clock
        val hour12 = (hour % 12).takeIf { it > 0 } ?: 12
        val isPm = hour >= 12

        NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.baseline_access_alarm_24)
            .setContentTitle("Cuckoo! ".repeat(hour12).trimEnd())
            .setContentText("It is now $hour12${if (isPm) "pm" else "am"}")
            .addAction(R.drawable.outline_cancel_24, "STOPPP!", cancelWorkIntent)
            .build()
            .run { notifManager.notify(notificationId, this) }

        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        notifManager.cancel(notificationId)
    }

    companion object {
        const val uniqueWorkName = "Cuckoo Clock"
        const val uniqueWorkTag = "cuckoo"
        const val notificationId = 73
        const val channelId = "73"
        const val channelName = "PeriodicWorker"
        const val channelDesc = "Annoys the heck out of you at xx:00."

        fun getWorkRequest(millisecondsDelay: Long) =
            PeriodicWorkRequestBuilder<PeriodicWorker>(1, TimeUnit.HOURS)
                .setInitialDelay(millisecondsDelay, TimeUnit.MILLISECONDS)
                .addTag(uniqueWorkTag)
                .build()
    }
}