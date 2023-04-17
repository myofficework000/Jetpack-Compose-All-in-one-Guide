package com.example.jetpack_compose_all_in_one.ui.theme

import android.media.RingtoneManager
import android.net.Uri
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.jetpack_compose_all_in_one.BottomNavBar
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.SimpleIconButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainerOfApp(
    isOffline: Boolean,
    playMusicFunc: (Uri) -> Unit,
    stopMusicFunc: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }, actions = {
                SimpleIconButton(R.drawable.baseline_play_circle_outline_24) {
                    playMusicFunc(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                }
                SimpleIconButton(R.drawable.outline_stop_circle_24) {
                    stopMusicFunc()
                }
            })
        },
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