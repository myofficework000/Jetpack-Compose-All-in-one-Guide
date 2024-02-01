package com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.provider.Telephony
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.airplanemode.AirplaneModeReceiver
import com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.batterybroadcast.BatteryBroadcast
import com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.bluetooth.BluetoothReceiver
import com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.smsrecevier.SmsBroadcast
import com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.wifi.WifiReceiver
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.utils.LogicPager

@Composable
private fun BroadCastReceiverContent() {
    val currentPage = rememberSaveable { mutableStateOf(0) }

    LogicPager(
        pageCount = 5,
        currentPage = currentPage
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LessonHeader(
                stringArrayResource(R.array.broadcast_header_text)[currentPage.value],
                Modifier
                    .fillMaxWidth()
                    .padding(dp_15),
                TextAlign.Center
            )

            when (currentPage.value) {
                0 -> BluetoothComponent()
                1 -> AirplaneModeComponent()
                2 -> WifiComponent()
                3 -> SmsReceivedComponent()
                4 -> BatteryReceivedComponent()
            }
        }
    }
}


@Preview
@Composable
fun BroadcastReceiverScreen() {
    BroadCastReceiverContent()
}

@Composable
fun WifiComponent() {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val wifiReceiver = WifiReceiver()
        val filters = IntentFilter()
        filters.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        context.registerReceiver(wifiReceiver, filters)

        onDispose {
            context.unregisterReceiver(wifiReceiver)
        }
    }
}

@Composable
fun BluetoothComponent() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val bluetoothReceiver = BluetoothReceiver()
        val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        context.registerReceiver(bluetoothReceiver, intentFilter)

        onDispose {
            context.unregisterReceiver(bluetoothReceiver)
        }
    }


}

@Composable
fun AirplaneModeComponent() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val receiver = AirplaneModeReceiver()
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

}

@Composable
fun SmsReceivedComponent() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val receiver = SmsBroadcast()
        val intentFilter = IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}

@Composable
fun BatteryReceivedComponent() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val receiver = BatteryBroadcast()
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}