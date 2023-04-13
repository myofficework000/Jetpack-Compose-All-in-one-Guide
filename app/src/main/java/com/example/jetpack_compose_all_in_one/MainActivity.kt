package com.example.jetpack_compose_all_in_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.counter.CounterAppWithService
import com.example.jetpack_compose_all_in_one.counter.CounterAppWithoutService
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme

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
                    CounterAppWithService()
                    //CounterAppWithoutService()
                    //HorizontalSimpleList(getCountries())
                    //VerticalSimpleList(getCountries())
                    //SimpleVerticalGridList(getCountries())
                    //VerticalList(getCountries())
                    //SimpleDialog()
                    //InputFields()
                    //BottomSheet()
                    NavigationDrawer()
                    BottomNavBar()
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