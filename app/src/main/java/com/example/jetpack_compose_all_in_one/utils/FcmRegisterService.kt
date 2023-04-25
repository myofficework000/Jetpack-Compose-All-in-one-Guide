package com.example.jetpack_compose_all_in_one.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.BufferedWriter
import java.io.OutputStreamWriter
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
            val conn = URL(fcmApiUrl).openConnection() as HttpsURLConnection

            conn.apply {
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
                .put("to", "/topics/$recipientUserId")

            // This is where the coroutine is suspended for a long time.
            val outputStream = BufferedOutputStream(conn.outputStream)
            BufferedWriter( OutputStreamWriter(outputStream, "utf-8") ).apply {
                write(body.toString())
                flush()
                close()
            }
            outputStream.close()

            // This runs after the post request is complete
            /*when (conn.responseCode){
                200 -> {
                    // Do something with this one
                    conn.inputStream
                }
                in 400..499 -> {
                    // Same here
                    conn.errorStream
                }
            }*/
        }

        fun subscribeToUser(userFcmToken: String, onSubscribe: () -> Unit) {
            Firebase.messaging.subscribeToTopic(userFcmToken)
                .addOnCompleteListener { onSubscribe() }
        }
    }
}