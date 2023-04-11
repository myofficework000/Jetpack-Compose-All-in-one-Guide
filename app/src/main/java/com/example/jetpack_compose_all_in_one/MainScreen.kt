package com.example.jetpack_compose_all_in_one

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    Scaffold (
        topBar = {
        SmallTopAppBar(
            navigationIcon =  {
                IconButton(onClick = {}) {
                    Icon( imageVector = Icons.Default.Menu,
                    contentDescription =  "Menu Icon")
                }
            },
            title = { 
                Text(text = "JetPack Compose")
            }
        )
    },
        content = {

        }
    )
}

