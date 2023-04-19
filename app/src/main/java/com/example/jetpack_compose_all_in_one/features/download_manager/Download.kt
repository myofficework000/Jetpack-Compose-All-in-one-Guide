package com.example.jetpack_compose_all_in_one.features.download_manager

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.work.*
import com.example.jetpack_compose_all_in_one.utils.extractFileName
import java.io.File

class Download(
    var url: String,
    private val applicationContext: Context,
    private val failureCallback: () -> Unit = {},
    private val successCallback: () -> Unit = {}
) {
    fun start() {
        if (url.isBlank()) {
            // Will do better checking later
            println("URL not set")
            return
        }

        if (startDownload(url)) startProgressIndicator()
        /*if( downloadRoot?.exists() == true ) {
            var canStart = true
            *//*if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {

            } else canStart = true*//*

            if (canStart) {

            } else {
                println("No access to storage.")
                failureCallback()
            }
        } else {
            // Add more stuff to this
            println("Cannot access Download folder.")
            failureCallback()
        }*/
    }

    private fun startDownload(url: String): Boolean {
        val fileName = url.extractFileName()
        var downloadRequest: DownloadManager.Request? = null

        try {
            downloadRequest = DownloadManager.Request(Uri.parse(url)).apply {
                setTitle("File: $fileName")
                setDescription("Downloading file...")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                setRequiresCharging(false)
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }
        } catch (e: java.lang.Exception) {
            // Exception here pretty much means invalid url
            e.printStackTrace()
            return false
        }

        (applicationContext
            .getSystemService(ComponentActivity.DOWNLOAD_SERVICE) as DownloadManager
        ).enqueue(downloadRequest).also {
            DownloadWorker.downloadState.apply {
                this.fileName = fileName
                this.downloadId = it
            }
        }

        return true
    }

    private fun startProgressIndicator() {
        WorkManager.getInstance(applicationContext)
            .beginUniqueWork(
                "download_file",
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequestBuilder<DownloadWorker>()
                    .addTag("DownloadWorker")
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()
                    )
                    .build()
            )
            .enqueue()
    }
}