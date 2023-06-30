package com.example.jetpack_compose_all_in_one.ui.views.main_ui

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.features.download_manager.Download
import com.example.jetpack_compose_all_in_one.features.internet.InternetViewModel
import com.example.jetpack_compose_all_in_one.features.internet.NetworkState
import com.example.jetpack_compose_all_in_one.application_components.services.music_example.MusicBoundService
import com.example.jetpack_compose_all_in_one.application_components.services.music_example.MusicForegroundService
import com.example.jetpack_compose_all_in_one.third_party_lib.chat_gpt.viewmodel.ChatGPTViewModel
import com.example.jetpack_compose_all_in_one.ui.components.InputFields
import com.example.jetpack_compose_all_in_one.ui.components.MainContainerOfApp
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme
import com.example.jetpack_compose_all_in_one.ui.views.theming.ThemeViewModel
import com.example.jetpack_compose_all_in_one.ui.views.theming.Themes
import com.example.jetpack_compose_all_in_one.utils.isLocationAllowed
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
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

    private val connectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    private val internetViewModel by viewModels<InternetViewModel>()
    private val chatGPTViewModel by viewModels<ChatGPTViewModel>()
    private val themeViewModel by viewModels<ThemeViewModel>()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationCancelToken by lazy{ CancellationTokenSource() }



    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("MissingPermission") // isLocationAllowed() checked this, but Lint doesn't know.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            JetpackComposeAllInOneTheme(
                darkTheme = when (themeViewModel.themeMode) {
                    Themes.DEFAULT -> isSystemInDarkTheme()
                    Themes.LIGHT -> false
                    Themes.DARK -> true
                }
            ) {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val downloadObject = remember{
                        Download(
                            "",
                            applicationContext,
                            {
                                println("Download failed")
                            }
                        ) {
                            println("Download done")
                        }
                    }

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

                    /*
                                        Column {
                                            var timeFromAlarmSet: String? = null
                                            SimpleTextButton(buttonMessage = stringResource(id = R.string.set_alarm)) {
                                                setAlarm(context = this@MainActivity)
                                            }

                                            var selected1 by remember { mutableStateOf(false) }
                                            SwitchRow(name = "fetch alarm", change = selected1, onCheckedChange = {
                                                selected1 = it
                                                timeFromAlarmSet = getAlarmTime
                                            })

                                            var selected2 by remember { mutableStateOf(false) }
                                            timeFromAlarmSet.let { time ->
                                                SwitchRow(name = "$time", change = selected2, onCheckedChange = {
                                                    selected2 = it
                                                    cancelAlarm(this@MainActivity)
                                                })
                                            }
                                        }
                    */

                    MainContainerOfApp(
                        applicationContext,
                        internetViewModel,
                        themeViewModel,
                        {
                            if (!isLocationAllowed(this)) it(null)
                            else fusedLocationClient
                                .getCurrentLocation(
                                    Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                                    locationCancelToken.token)
                                .addOnCompleteListener { loc -> it(loc.result) }
                        },
                        {
                            startForegroundService(
                                playIntentForeground.putExtra(MusicForegroundService.name_arg, it.toString())
                            )
                        },
                        { stopService( playIntentForeground ) },
                        { musicBoundService?.startMusic(it) },
                        { musicBoundService?.stopMusic() },
                        { musicBoundService?.pauseMusic(it) },
                        { musicBoundService?.resumeMusic() },
                        downloadObject,
                        { downloadObject.url = it }
                    )
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

    override fun onResume() {
        super.onResume()
        connectivityManager.registerNetworkCallback(
            internetViewModel.networkRequest,
            internetViewModel.networkCallback
        )
        internetViewModel.networkState.value =
            if (connectivityManager.activeNetwork != null) NetworkState.Connected
            else NetworkState.Disconnected
    }

    override fun onPause() {
        super.onPause()
        connectivityManager.unregisterNetworkCallback(
            internetViewModel.networkCallback
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        JetpackComposeAllInOneTheme {
            InputFields()
        }
    }
}