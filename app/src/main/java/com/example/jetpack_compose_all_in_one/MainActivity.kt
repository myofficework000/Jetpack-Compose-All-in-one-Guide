package com.example.jetpack_compose_all_in_one

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.service_examples.music.MusicBackgroundService
import com.example.jetpack_compose_all_in_one.service_examples.music.MusicForegroundService
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme
import com.example.jetpack_compose_all_in_one.ui.theme.MainContainerOfApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                    MainContainerOfApp(
                        true,
                        {
                            startForegroundService(
                                Intent(this, MusicForegroundService::class.java).apply {
                                    putExtra(MusicForegroundService.name_arg, it.toString())
                                }
                            )
                        },
                        { stopService( Intent(this, MusicForegroundService::class.java) ) }
                    )
                }
            }
        }
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