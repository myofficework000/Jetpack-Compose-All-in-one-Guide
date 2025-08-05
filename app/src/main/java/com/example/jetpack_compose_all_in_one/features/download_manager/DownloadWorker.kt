package com.example.jetpack_compose_all_in_one.features.download_manager

import android.app.DownloadManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class DownloadWorker(
    val context: Context, parameter: WorkerParameters,
): CoroutineWorker(context, parameter) {
    private lateinit var receiver: BroadcastReceiver

    override suspend fun doWork(): Result =
        when (
            withContext(Dispatchers.Default) {
                downloadFile(
                    context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager,
                    downloadState.downloadId, // It doesn't check if the downloadId is valid. Careful.
                    downloadState
                )
            }
        ) {
            DownloadCompletionStatus.COMPLETED -> Result.success()
            DownloadCompletionStatus.FAILED -> Result.failure()
            else -> Result.retry()
        }

    private suspend fun downloadFile(
        downloadManager: DownloadManager,
        downloadId: Long,
        downloadState: DownloadState,
    ): DownloadCompletionStatus {
        registerReceiver()
        val notification = DownloadNotification(context)

        while (true) {
            downloadManager.query(DownloadManager.Query().setFilterById(downloadId)).apply {
                if (this.count < 1 ||
                    downloadState.status == DownloadStatus.TERMINATED
                ) {
                    close()
                    unregisterReceiver()
                    return DownloadCompletionStatus.FAILED
                }

                val colBytesDownloaded = getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                val colFileSize = getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                val colStatus = getColumnIndex(DownloadManager.COLUMN_STATUS)
                val colReason = getColumnIndex(DownloadManager.COLUMN_REASON)

                moveToFirst()
                when (getInt(colStatus)) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        unregisterReceiver()
                        return DownloadCompletionStatus.COMPLETED
                    }
                    DownloadManager.STATUS_FAILED -> {
                        unregisterReceiver()
                        // If it's 400, it's most likely it being an http instead of https.
                        println("Download fail reason: ${getInt(colReason)}")
                        return DownloadCompletionStatus.FAILED
                    }
                    else -> {}
                }
                downloadState.apply {
                    bytesDownloaded = getLong(colBytesDownloaded)
                    fileSizeByte = getLong(colFileSize)
                    status = DownloadState.statusFromDownloadManager(getInt(colStatus))!!
                }

                coroutineScope {
                    setForeground(
                        notification.update(
                            PendingIntent.getBroadcast(context, intentCodeCancel, Intent(
                                DownloadReceiver.DOWNLOAD_CANCEL
                            ).apply {
                                putExtra(DownloadReceiver.download_worker_id_arg, id.toString())
                                putExtra(DownloadReceiver.download_manager_id_arg, downloadId)
                            }, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT),
                            PendingIntent.getBroadcast(context, intentCodePause, Intent(
                                DownloadReceiver.DOWNLOAD_PAUSE
                            ), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT),
                            PendingIntent.getBroadcast(context, intentCodeResume, Intent(
                                DownloadReceiver.DOWNLOAD_RESUME
                            ), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT),
                            downloadState
                        )
                    )
                }

                close()
            }
        }
    }

    private fun registerReceiver() {
        receiver = DownloadReceiver()
        ContextCompat.registerReceiver(context, receiver, IntentFilter().apply {
            addAction(DownloadReceiver.DOWNLOAD_PAUSE)
            addAction(DownloadReceiver.DOWNLOAD_RESUME)
            addAction(DownloadReceiver.DOWNLOAD_CANCEL)
        }, ContextCompat.RECEIVER_NOT_EXPORTED)
    }

    private fun unregisterReceiver() {
        context.unregisterReceiver(receiver)
    }

    private enum class DownloadCompletionStatus {
        RETRY,
        COMPLETED,
        FAILED
    }

    companion object {
        val downloadState = DownloadState.getEmpty()

        const val intentCodePause = 6900
        const val intentCodeResume = 6901
        const val intentCodeCancel = 6902
    }
}