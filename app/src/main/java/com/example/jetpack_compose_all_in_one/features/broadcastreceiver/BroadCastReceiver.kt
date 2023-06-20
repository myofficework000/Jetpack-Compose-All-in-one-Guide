package com.example.jetpack_compose_all_in_one.features.broadcastreceiver

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.example.jetpack_compose_all_in_one.features.broadcastreceiver.airplanemode.AirplaneModeReceiver
import com.example.jetpack_compose_all_in_one.features.broadcastreceiver.bluetooth.BluetoothReceiver
import com.example.jetpack_compose_all_in_one.features.broadcastreceiver.wifi.WifiReceiver

@Composable
fun BroadcastReceiverScreen() {

    Column {
        Text("Broadcast Receiver Screen")
        BluetoothComponent()
        AirplaneModeComponent()
        WifiComponent()
    }

}

@Composable
fun WifiComponent(){
    val context = LocalContext.current
    DisposableEffect(Unit){
        val wifiReceiver = WifiReceiver()
        val intentFilter = IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        context.registerReceiver(wifiReceiver, intentFilter)

        onDispose {
            context.unregisterReceiver(wifiReceiver)
        }
    }
}

@Composable
fun BluetoothComponent(){
    val context = LocalContext.current

    DisposableEffect(Unit){
        val bluetoothReceiver = BluetoothReceiver()
        val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        context.registerReceiver(bluetoothReceiver,intentFilter)

        onDispose {
            context.unregisterReceiver(bluetoothReceiver)
        }
    }


}

@Composable
fun AirplaneModeComponent(){
    val context = LocalContext.current

    DisposableEffect(Unit){
        val receiver = AirplaneModeReceiver()
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        context.registerReceiver(receiver,intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

}