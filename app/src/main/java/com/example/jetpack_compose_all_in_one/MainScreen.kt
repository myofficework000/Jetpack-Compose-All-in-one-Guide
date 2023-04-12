package com.example.jetpack_compose_all_in_one

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(callback:() -> Unit) {
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = "JetPack Compose")
            },
                navigationIcon = {
                    IconButton(onClick = callback) {
                        Icon( imageVector = Icons.Default.Menu,
                            contentDescription =  "Menu Icon")
                    }
                },
            )
    },
        content = {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(){
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

