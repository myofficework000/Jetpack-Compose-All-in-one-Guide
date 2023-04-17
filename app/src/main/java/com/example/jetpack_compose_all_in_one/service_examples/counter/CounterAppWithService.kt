package com.example.jetpack_compose_all_in_one.service_examples.counter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterAppWithService() {

    var counter by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val intentService = remember { Intent(context, CounterAppService::class.java) }
    val broadcast = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                counter = intent?.getIntExtra("counter", 0) ?: 0
            }
        }
    }
    LaunchedEffect(key1 = true) {
        val intentFilter = IntentFilter("counter_screen")
        context.registerReceiver(broadcast, intentFilter)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$counter", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Button(onClick = {
                context.startService(intentService)
            }) {
                Text(text = "Start Service")

            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                context.stopService(intentService)
            }) {
                Text(text = "Stop Service")
            }
        }
    }

}