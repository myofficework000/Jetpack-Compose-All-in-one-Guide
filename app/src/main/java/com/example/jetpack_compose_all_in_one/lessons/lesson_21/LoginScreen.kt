package com.example.jetpack_compose_all_in_one.lessons.lesson_21

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.jetpack_compose_all_in_one.lessons.lesson_21.navigation.Routes.DASHBOARD
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = UserDataStore(context)

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") }
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") }
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") }
            )

            Button(onClick = {
                scope.launch {
                    dataStore.saveName(name)
                    dataStore.saveEmail(email)
                }
                navController.navigate(DASHBOARD)
            },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "LOGIN")
            }
        }
    }

}