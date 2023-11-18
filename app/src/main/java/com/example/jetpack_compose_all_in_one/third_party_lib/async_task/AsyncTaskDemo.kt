package com.example.jetpack_compose_all_in_one.third_party_lib.async_task

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.os.Message

fun main() {
    AsyncTaskDemo().execute()
}

class AsyncTaskDemo : AsyncTask<Int, Int, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        //handle your UI task
    }


    override fun doInBackground(vararg p0: Int?): String {
        //handle your background task

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                // Update UI with the calculation result here
            }
        }
        return ""
    }

    @SuppressLint("WrongThread")
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        publishProgress(values.size)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        //handle the UI task after finishing the background task...
    }
}