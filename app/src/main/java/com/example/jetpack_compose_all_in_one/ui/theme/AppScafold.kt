package com.example.jetpack_compose_all_in_one.ui.theme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.jetpack_compose_all_in_one.BottomNavBar
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.TopAppBar


@Composable
fun MainContainerOfApp(isOffline: Boolean) {

    Scaffold(
        bottomBar = { BottomNavBar()},
        snackbarHost = { SnackbarShow(isOffline) }
    ) {
        // Screen content
        if (isOffline) {
           NetworkErrorDialog()
        } else {
            Text("Internet available")
        }
    }
}