package com.example.jetpack_compose_all_in_one.application_components.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.jetpack_compose_all_in_one.application_components.services.IntentServiceDemo.Companion.URL_OF_DOWNLOAD_FILE

class IntentServiceDemo : IntentService("IntentServiceDemo") {

    companion object {
        const val ACTION_DOWNLOAD_FILE = "com.example.intentdemo.action.DOWNLOAD_FILE"
        const val EXTRA_FILE_URL = "com.example.intentdemo.extra.FILE_URL"
        const val URL_OF_DOWNLOAD_FILE = "https://example.com/samplefile.txt"
    }

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_DOWNLOAD_FILE -> {
                val fileUrl = intent.getStringExtra(EXTRA_FILE_URL)
                downloadFile(fileUrl)
            }
        }
    }

    private fun downloadFile(fileUrl: String?) {
        // Simulate a long-running task such as downloading a file
        Log.d("IntentServiceDemo", "Downloading file from $fileUrl")

        // Simulate the download process
        for (progress in 0..100 step 10) {
            // Update progress or perform other tasks
            Log.d("IntentServiceDemo", "Progress: $progress%")
            try {
                Thread.sleep(1000) // Simulate time-consuming task
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }

        // Download completed
        Log.d("IntentServiceDemo", "Download complete for file from $fileUrl")

        // You can send a broadcast or update UI here if needed
        // For simplicity, let's log the completion message.
    }
}

fun invokeIntentService(context: Context) {
    val intent = Intent(context, IntentServiceDemo::class.java).apply {
        action = IntentServiceDemo.ACTION_DOWNLOAD_FILE
        putExtra(IntentServiceDemo.EXTRA_FILE_URL, URL_OF_DOWNLOAD_FILE)
    }
    context.startService(intent)
}
