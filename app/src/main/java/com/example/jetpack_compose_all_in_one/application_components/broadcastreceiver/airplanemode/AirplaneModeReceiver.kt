package com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.airplanemode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
       if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
           val isAirplaneModeEnabled = intent.getBooleanExtra("state",false)
           if(isAirplaneModeEnabled){
               Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show()
           } else {
               Toast.makeText(context, "Airplane Mode Disabled", Toast.LENGTH_LONG).show()
           }
       }
    }
}