package com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.batterybroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class BatteryBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: 1
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: 1
        val batteryPercentage = level * 100 / scale.toFloat()

        Log.i("Battery Broadcast", "Your battery is at: $batteryPercentage %")
    }
}