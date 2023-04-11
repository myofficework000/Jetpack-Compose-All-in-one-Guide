package com.example.jetpack_compose_all_in_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.data.getCountries
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme
import kotlinx.coroutines.launch

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
                    val coroutineScope = rememberCoroutineScope()
                    val sheetState = rememberModalBottomSheetState()

                    DemoSheet(
                        sheetState = sheetState,
                        onDismiss = {
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    })
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                sheetState.partialExpand()
                            }
                        }
                    )
                    {
                        Text(text = "Open Bottom Sheet")
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAllInOneTheme {
        InputFields()
        TopAppBar()
        /*Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            val coroutineScope = rememberCoroutineScope()
            val sheetState = rememberModalBottomSheetState()

            DemoSheet(
                sheetState = sheetState,
                onDismiss = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                })
            Button(
                onClick = {
                    coroutineScope.launch {
                        sheetState.partialExpand()
                    }
                }
            )
            {
                Text(text = "Open Bottom Sheet")
            }
        }*/
    }
}