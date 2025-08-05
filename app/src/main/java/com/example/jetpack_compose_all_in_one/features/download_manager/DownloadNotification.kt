package com.example.jetpack_compose_all_in_one.features.download_manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.utils.toReadableFileSize

class DownloadNotification(
    private val applicationContext: Context
) {
    init {
        // createNotificationChannel won't run at all if it already exists.
        (applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE)

                as NotificationManager
        ).createNotificationChannel(
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = channelDesc
            }
        )
    }

    // We need a fresh notification each time it updates, or the notification
    //      can't do anything to newer Workers and Download instances.
    private fun getBase() = NotificationCompat.Builder(applicationContext, channelId)
        .setSmallIcon(R.drawable.baseline_download_24)
        .setContentTitle("Downloading file...")
        .setOngoing(true)
        .setAutoCancel(false)
        .setOnlyAlertOnce(true)

    // Every single time a notification changes, it's actually creating a new one and
    //      push it to the foreground. So whether it's the creation or subsequent updates,
    //      they all run update().
    // NotificationCompat makes it more compatible with more versions is all.
    fun update(
        pendingIntentCancel: PendingIntent,
        pendingIntentPause: PendingIntent,
        pendingIntentResume: PendingIntent,
        downloadState: DownloadState
    ): ForegroundInfo = ForegroundInfo(
        notificationId,
        getBase().apply {
            setContentTitle(
                "Downloading: ${downloadState.fileName}"
            )
            setContentText(
                "Progress: ${DownloadWorker.downloadState.bytesDownloaded.toReadableFileSize()} / ${DownloadWorker.downloadState.fileSizeByte.toReadableFileSize()}"
            )

            val progressRelative = (
                    DownloadWorker.downloadState.bytesDownloaded /
                            DownloadWorker.downloadState.fileSizeByte * 10000
                    ).toInt()

            clearActions()
            when (downloadState.status) {
                DownloadStatus.EMPTY -> {}
                DownloadStatus.PENDING -> {
                    setProgress(100,0,true)
                    addAction(R.drawable.baseline_stop_24, "Cancel", pendingIntentCancel)
                }
                DownloadStatus.STARTED -> {
                    setProgress(10000, progressRelative, false)
                    addAction(R.drawable.baseline_pause_24, "Pause", pendingIntentPause)
                    addAction(R.drawable.baseline_stop_24, "Cancel", pendingIntentCancel)
                }
                DownloadStatus.PAUSED -> {
                    setProgress(10000, progressRelative, false)
                    addAction(R.drawable.baseline_play_arrow_24, "Resume", pendingIntentResume)
                    addAction(R.drawable.baseline_stop_24, "Cancel", pendingIntentCancel)
                }
                DownloadStatus.TERMINATED -> {
                    setProgress(10000, progressRelative, false)
                }
                DownloadStatus.COMPLETED -> {
                    setProgress(100,100,false)
                }
            }
        }.build()
    )

    companion object {
        const val notificationId = 69
        const val channelId = "69"
        const val channelName = "Download Manager"
        const val channelDesc = "For displaying the progress bar for the download manager."
    }
}