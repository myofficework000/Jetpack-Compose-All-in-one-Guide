package com.example.jetpack_compose_all_in_one.ui.theme

import android.media.RingtoneManager
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.*
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.alarm.AlarmMainUI
import com.example.jetpack_compose_all_in_one.download_manager.Download


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainerOfApp(
    isOffline: Boolean,
    playMusicFuncForeground: (Uri) -> Unit,
    stopMusicFuncForeground: () -> Unit,
    playMusicFuncBound: (Uri) -> Unit,
    stopMusicFuncBound: () -> Unit,
    pauseMusicFuncBound: (Long) -> Unit,
    resumeMusicFuncBound: () -> Unit,
    downloadFileObj: Download,
    setDownloadUrlFunc: (String) -> Unit
) {
    var inputUrl by remember{ mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }, actions = {
                SimpleIconButton(R.drawable.baseline_play_circle_outline_24) {
                    playMusicFuncForeground(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                }
                SimpleIconButton(R.drawable.outline_stop_circle_24) {
                    stopMusicFuncForeground()
                }
                AlarmMainUI()
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
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                GridCells.Fixed(2),
                Modifier
                    .background(Color(0.314f, 0.29f, 0.29f, 0.725f))
                    .padding(16.dp)
            ) {
                item{
                    SimpleTextButton("Start") { playMusicFuncBound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)) }
                }
                item{
                    SimpleTextButton("Stop") { stopMusicFuncBound() }
                }
                item{
                    SimpleTextButton("Pause (10s)") { pauseMusicFuncBound(10000L) }
                }
                item{
                    SimpleTextButton("Resume") { resumeMusicFuncBound() }
                }
            }

            Column() {
                TextField(
                    inputUrl,
                    onValueChange = {url ->
                        inputUrl = url
                        setDownloadUrlFunc(url)
                    },
                placeholder = { Text("Enter URL here.") })
            }
            SimpleTextButton(buttonMessage = "Download") { downloadFileObj.start() }
        }
    }
}