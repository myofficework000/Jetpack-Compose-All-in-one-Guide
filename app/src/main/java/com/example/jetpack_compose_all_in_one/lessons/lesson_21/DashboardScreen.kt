package com.example.jetpack_compose_all_in_one.lessons.lesson_21

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen() {

    val context = LocalContext.current
    val dataStore = UserDataStore(context)
    val scope = rememberCoroutineScope()
    val savedName = dataStore.getName.collectAsState(initial = "Empty")
    val savedEmail = dataStore.getEmail.collectAsState(initial = "Empty")

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Text(text = savedName.value!!)
            Text(text = savedEmail.value!!)

            Button(onClick = {
                scope.launch {
                    dataStore.clearData()
                }
            }) {
                Text(text = "Clear data store")
            }
        }
    }

}