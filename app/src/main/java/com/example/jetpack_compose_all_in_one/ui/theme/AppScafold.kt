package com.example.jetpack_compose_all_in_one.ui.theme

import android.media.RingtoneManager
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.*
import com.example.jetpack_compose_all_in_one.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainerOfApp(
    isOffline: Boolean,
    playMusicFuncForeground: (Uri) -> Unit,
    stopMusicFuncForeground: () -> Unit,
    playMusicFuncBound: (Uri) -> Unit,
    stopMusicFuncBound: () -> Unit,
    pauseMusicFuncBound: (Long) -> Unit,
    resumeMusicFuncBound: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }, actions = {
                SimpleIconButton(R.drawable.baseline_play_circle_outline_24) {
                    playMusicFuncForeground(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                }
                SimpleIconButton(R.drawable.outline_stop_circle_24) {
                    stopMusicFuncForeground()
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
        Box(
            Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .background(Color(0.314f, 0.29f, 0.29f, 0.725f))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleTextButton("Start") { playMusicFuncBound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)) }
                SimpleTextButton("Stop") { stopMusicFuncBound() }
                SimpleTextButton("Pause (10s)") { pauseMusicFuncBound(10000L) }
                SimpleTextButton("Resume") { resumeMusicFuncBound() }
            }
        }
    }
}