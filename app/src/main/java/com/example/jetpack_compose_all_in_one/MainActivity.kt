package com.example.jetpack_compose_all_in_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.data.getCountries
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAllInOneTheme {
                // A surface container using the 'background' color from the theme

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column {
                        //Greeting("Android")
                        HorizontalSimpleList(getCountries())
                        VerticalSimpleList(getCountries())
                        SimpleVerticalGridList(getCountries())
                        InputFields()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAllInOneTheme {
        InputFields()
    }
}