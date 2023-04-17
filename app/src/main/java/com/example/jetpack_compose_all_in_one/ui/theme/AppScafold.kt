package com.example.jetpack_compose_all_in_one.ui.theme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.jetpack_compose_all_in_one.BottomNavBar
import com.example.jetpack_compose_all_in_one.R


@Composable
fun MainContainerOfApp(isOffline: Boolean) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { ShowSnackbar( true, stringResource(id = R.string.no_internet_connected), snackbarHostState) }
    ) {
        // Screen content
        if (isOffline) {
           // NetworkErrorDialog()
        } else {
            Text("Internet available")
        }
    }
}