package com.example.jetpack_compose_all_in_one

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.alarm.setAlarm
import com.example.jetpack_compose_all_in_one.service_examples.music.MusicBoundService
import com.example.jetpack_compose_all_in_one.service_examples.music.MusicForegroundService
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme
import com.example.jetpack_compose_all_in_one.ui.theme.MainContainerOfApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val playIntentForeground by lazy {
        Intent(this, MusicForegroundService::class.java)
    }
    private var musicBoundService: MusicBoundService? = null

    private val bindConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder) {
            musicBoundService = (p1 as MusicBoundService.RemoteControl).service
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            println("Ouch someone shut the service for no reason.")
            musicBoundService = null
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAllInOneTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //CounterAppWithService()
                    //CounterAppWithoutService()
                    //HorizontalSimpleList(getCountries())
                    //VerticalSimpleList(getCountries())
                    //SimpleVerticalGridList(getCountries())
                    //VerticalList(getCountries())
                    //SimpleDialog()
                    //InputFields()
                    //BottomSheet()
                    //NavigationDrawer()
                    //BottomNavBar()
                    //RegistrationForm()
//                    MainContainerOfApp(
//                        true,
//                        {
//                            startForegroundService(
//                                playIntentForeground.putExtra(MusicForegroundService.name_arg, it.toString())
//                            )
//                        },
//                        { stopService( playIntentForeground ) },
//                        { musicBoundService?.startMusic(it) },
//                        { musicBoundService?.stopMusic() },
//                        { musicBoundService?.pauseMusic(it) },
//                        { musicBoundService?.resumeMusic() }
//                    )
                    SimpleTextButton(buttonMessage = stringResource(id = R.string.set_alarm)) {
                        setAlarm(context = this)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        bindService(
            Intent(this, MusicBoundService::class.java),
            bindConnection,
            BIND_AUTO_CREATE
        )
    }

    override fun onStop() {
        super.onStop()
        unbindService(bindConnection)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        JetpackComposeAllInOneTheme {
            InputFields()
        }
    }
}