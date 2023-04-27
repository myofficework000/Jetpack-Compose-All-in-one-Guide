package com.example.jetpack_compose_all_in_one.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.views.main_ui.MainActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class FcmRegisterService: FirebaseMessagingService() {
    private val tokenRef = Firebase.database.getReference("user_tokens")
    private val sharedPreferences by lazy{ getSharedPreferences(fcmPref, MODE_PRIVATE).edit() }
    private val job = SupervisorJob()
    private val ioDispatcher = Dispatchers.IO

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val userId = tokenRef.push().key.toString()
        sharedPreferences.putString(userIdArg, userId)
        tokenRef.child(userId).setValue(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        println("Received message: ${message.data}")
        Log.i("tag", "Received message")

        Log.e("onMessageReceived: ", message.data.toString())

        message.let {
            val title = it.data["title"]
            val content = it.data["content"]
            val imageUrl = it.data["image"]
            val bitmap = getBitmapFromURL(imageUrl)


            val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            val pendingIntent = PendingIntent.getActivity(
                applicationContext, 0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                checkNotificationChannel("1")
            }

            val notification = NotificationCompat.Builder(applicationContext, "1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(defaultSound)
                .setLargeIcon(bitmap)

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notification.build())
        }
    }

    private fun getBitmapFromURL(strURL: String?): Bitmap? {
        return try {
            val url = URL(strURL)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkNotificationChannel(channelId: String) {
        val notificationChannel = NotificationChannel(
            channelId,
            getString(R.string.app_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description = "CHANNEL_DESCRIPTION"
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    companion object {
        const val fcmPref = "fcm"
        const val userIdArg = "userId"
        const val fcmApiUrl = "https://fcm.googleapis.com/fcm/send"
        const val placeholderServerKey = "AAAAVN1gILg:APA91bESm42MCkHxynke3-58IP9_EXNyB6QSfYvhAAbNtT-qLLHzlQrVdpo5i8uC7euUUG1r76Ru-J9NCLibGiagpCWcXKubnXNvaFHhqGESeyMjlXKwxPsxwut4dpmsHFXeRyZ6D5Pd"

        // All these warning tells us to use IO thread. But this whole function
        //      runs on Dispatcher.IO so don't worry about it.
        suspend fun sendMessage(recipientUserId: String, message: String) {
            val httpsURLConnection =
                withContext(Dispatchers.IO) {
                    URL(fcmApiUrl).openConnection()
                } as HttpsURLConnection

            httpsURLConnection.apply {
                readTimeout = 10000
                connectTimeout = 15000
                requestMethod = "POST"
                doInput = true
                doOutput = true
                setRequestProperty("authorization", "key=$placeholderServerKey")
                setRequestProperty("Content-Type", "application/json")
            }

            // Creating the JSON with post params
            val body = JSONObject()
                .put("data", JSONObject().put("message", message))
                .put("to", "$recipientUserId")

            // This is where the coroutine is suspended for a long time.
            val outputStream = BufferedOutputStream(httpsURLConnection.outputStream)
            withContext(Dispatchers.IO) {
                BufferedWriter(OutputStreamWriter(outputStream, "utf-8")).apply {
                    write(body.toString())
                    flush()
                    close()
                }
            }
            withContext(Dispatchers.IO) {
                outputStream.close()
            }

            // This runs after the post request is complete
            when (httpsURLConnection.responseCode){
                200 -> {
                    // Do something with this one
                    httpsURLConnection.inputStream
                    Log.i("tag","Success")
                    Log.i("tag", httpsURLConnection.responseMessage)
                }
                in 400..499 -> {
                    // Same here
                    httpsURLConnection.errorStream
                    Log.i("tag","Failure")
                }
            }
        }

        fun subscribeToUser(userFcmToken: String, onSubscribe: () -> Unit) {
            Firebase.messaging.subscribeToTopic(userFcmToken)
                .addOnCompleteListener { onSubscribe() }
        }
    }


}