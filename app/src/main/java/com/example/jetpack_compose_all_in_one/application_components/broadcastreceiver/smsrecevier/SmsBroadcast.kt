package com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.smsrecevier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log

class SmsBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            Log.i("SMS Broadcast","You have received a SMS message")
        }
    }
}