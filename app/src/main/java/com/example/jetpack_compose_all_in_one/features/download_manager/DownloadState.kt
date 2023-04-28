package com.example.jetpack_compose_all_in_one.features.download_manager

import android.app.DownloadManager

class DownloadState(
    var downloadId: Long,
    var fileName: String,
    var fileSizeByte: Long,
    var bytesDownloaded: Long,
    var status: DownloadStatus
) {
    companion object {
        fun statusFromDownloadManager(status: Int) = when (status) {
            DownloadManager.STATUS_FAILED -> DownloadStatus.TERMINATED
            DownloadManager.STATUS_SUCCESSFUL -> DownloadStatus.COMPLETED
            DownloadManager.STATUS_PAUSED -> DownloadStatus.PAUSED
            DownloadManager.STATUS_PENDING -> DownloadStatus.PENDING
            DownloadManager.STATUS_RUNNING -> DownloadStatus.STARTED
            else -> null
        }

        fun getEmpty() = DownloadState(
            -1,
            "",
            0,
            0,
            DownloadStatus.EMPTY
        )
    }
}

enum class DownloadStatus {
    EMPTY,
    PENDING,
    STARTED,
    PAUSED,
    TERMINATED,
    COMPLETED
}