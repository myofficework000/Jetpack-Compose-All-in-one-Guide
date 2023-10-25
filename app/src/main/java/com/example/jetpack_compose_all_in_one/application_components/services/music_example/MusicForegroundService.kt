package com.example.jetpack_compose_all_in_one.application_components.services.music_example

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.provider.Settings
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.views.main_ui.MainActivity

class MusicForegroundService : Service() {
    private val mediaPlayer by lazy {
        MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI).apply {
            setOnCompletionListener { stopSelf() }
        }
    }

    override fun onBind(p0: Intent?) = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra(name_arg)?.let {
            mediaPlayer.apply {
                reset()
                setDataSource(this@MusicForegroundService, Uri.parse(it))
                prepare()
                start()
            }
        }

        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            notif_channel
        )

        val notification: Notification = Notification.Builder(this, notif_channel_id.toString())
            .setContentTitle("Now playing")
            .setContentText("Something")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentIntent(
                PendingIntent.getActivity(
                    this,
                    0,
                    Intent(this, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE
                )
            )
            .build()

        startForeground(notif_channel_id, notification)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    companion object {
        const val name_arg = "name"
        const val notif_channel_id = 69
        val notif_channel = NotificationChannel(
            notif_channel_id.toString(),
            "Play music",
            NotificationManager.IMPORTANCE_DEFAULT
        )
    }
}