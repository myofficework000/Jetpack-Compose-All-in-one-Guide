package com.example.jetpack_compose_all_in_one.features.download_manager

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.work.WorkManager
import java.util.UUID

class DownloadReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent) {
        when (p1.action) {


            // Warning: Pause and resume isn't working for now.


            DOWNLOAD_PAUSE -> {
                DownloadWorker.downloadState.status = DownloadStatus.PAUSED
                pauseOrResumeDownload(p0, true)
            }
            DOWNLOAD_RESUME -> {
                DownloadWorker.downloadState.status = DownloadStatus.STARTED
                pauseOrResumeDownload(p0, false)
            }
            DOWNLOAD_CANCEL -> {
                val downloadWorkerId = p1.getStringExtra(download_worker_id_arg)
                val downloadManagerId = p1.getLongExtra(download_manager_id_arg, -1)

                WorkManager.getInstance(p0).cancelWorkById(UUID.fromString(downloadWorkerId))
                if (downloadManagerId >= 0) {
                    (p0.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
                        .remove(downloadManagerId)
                }
            }
        }
    }

    // Note that for simplicity, this pauses ALL downloads currently running.
    private fun pauseOrResumeDownload(context: Context, isPausing: Boolean) {
        try {
            context.contentResolver.update(
                Uri.parse("content://downloads/all_downloads"),
                ContentValues().apply {
                    put("control", if (isPausing) 1 else 0) },
                "title=?",
                arrayOf(DownloadWorker.downloadState.fileName)
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        const val DOWNLOAD_PAUSE = "ACTION_DOWNLOAD_PAUSE"
        const val DOWNLOAD_RESUME = "ACTION_DOWNLOAD_RESUME"
        const val DOWNLOAD_CANCEL = "ACTION_DOWNLOAD_CANCEL"

        const val download_worker_id_arg = "download_worker"
        const val download_manager_id_arg = "download_manager"
    }
}