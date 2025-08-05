package com.example.jetpack_compose_all_in_one.jetpack_components.work_manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.jetpack_compose_all_in_one.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class OneTimeWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    private val notifManager: NotificationManager

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
    }

    private fun notificationBase() =
        NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.baseline_access_alarm_24)
            .setContentTitle("Alarm")
            .setOnlyAlertOnce(true)

    override suspend fun doWork(): Result {
        withContext(Dispatchers.Default) {
            var secondsLeft = 10
            while (secondsLeft > 0) {
                notifManager.notify(notificationId,
                    notificationBase().run {
                        setContentText(
                            "Time Left: $secondsLeft seconds"
                        )
                        clearActions()
                        setDeleteIntent(
                            WorkManager.getInstance(applicationContext)
                                .createCancelPendingIntent(id)
                        )
                        build()
                    }
                )

                delay(1000)
                secondsLeft--
            }
        }

        notifManager.notify(
            notificationId,
            notificationBase()
                .setContentText("Time's up")
                .clearActions()
                .build()
        )

        return Result.success()
    }

    companion object {
        const val uniqueWorkName = "Countdown: 10s"
        const val uniqueWorkTag = "countdown"
        const val notificationId = 72
        const val channelId = "72"
        const val channelName = "OneTimeWorker"
        const val channelDesc = "Just to pop a notification."
    }
}