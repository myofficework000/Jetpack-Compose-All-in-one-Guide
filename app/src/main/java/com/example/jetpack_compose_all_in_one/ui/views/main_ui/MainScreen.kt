package com.example.jetpack_compose_all_in_one

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.jetpack_compose_all_in_one.ui.components.SimpleBottomSheet
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavBar(){
    var selectedIndex by remember { mutableStateOf(0)}
    val menuList = listOf(
        Icons.Filled.Home to "Home",
        Icons.Filled.Person to "Account",
        Icons.Filled.Menu to "Menu"
    )
        NavigationBar {
            menuList.forEachIndexed { index, data ->
                NavigationBarItem(
                    selected = selectedIndex == index ,
                    onClick = { selectedIndex = index },
                    label = { Text(text = data.second) },
                    icon = {
                        Icon(imageVector = data.first, contentDescription = data.second)
                    }
                )
            }
    }
}


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

    SimpleBottomSheet(
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

