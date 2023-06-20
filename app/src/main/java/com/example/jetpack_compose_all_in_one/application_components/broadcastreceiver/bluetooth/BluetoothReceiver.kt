package com.example.jetpack_compose_all_in_one.application_components.broadcastreceiver.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            when(it.action){
                BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED -> {
                    val connectionState = it.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE,-1)
                    // Handle Bluetooth connection state change
                    handleConnectionState(connectionState,context)
                }
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val bluetoothState = it.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
                    // Handle Bluetooth state change
                    handleBluetoothStateChange(bluetoothState,context)

                }
            }
        }
    }


    private fun handleConnectionState(connectionState: Int, context: Context?) {
        when(connectionState){
            BluetoothAdapter.STATE_CONNECTED -> {
                Log.i("BLUETOOTH", "Device connected")
            }
            BluetoothAdapter.STATE_DISCONNECTED -> {
                Log.i("BLUETOOTH", "Device disconnected")
            }
        }

    }

    private fun handleBluetoothStateChange(bluetoothState: Int, context: Context?) {
        when(bluetoothState){
            BluetoothAdapter.STATE_ON -> {
                Log.i("BLUETOOTH" , "Bluetooth is on")
            }
            BluetoothAdapter.STATE_OFF -> {
                Log.i("BLUETOOTH", "Bluetooth is off")
            }
        }

    }
}