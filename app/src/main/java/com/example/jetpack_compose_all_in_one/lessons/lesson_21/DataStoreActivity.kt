package com.example.jetpack_compose_all_in_one.lessons.lesson_21

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.jetpack_compose_all_in_one.lessons.lesson_21.navigation.MyApp
import com.example.jetpack_compose_all_in_one.lessons.lesson_21.ui.theme.JetpackComposeAllInOneTheme

class DataStoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAllInOneTheme {
                val context = LocalContext.current
                val userStore = UserDataStore(context)

                // Collect the DataStore values using collectAsState
                val savedNameState = userStore.getName.collectAsState(initial = null)
                val savedEmailState = userStore.getEmail.collectAsState(initial = null)

                // Extract the values from the State objects
                val savedName = savedNameState.value
                val savedEmail = savedEmailState.value

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (savedName != "Empty" && savedEmail != "Empty") {
                        DashboardScreen()
                    } else {
                        MyApp()
                    }
                }
            }
        }
    }
}