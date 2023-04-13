package com.example.jetpack_compose_all_in_one

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
import com.example.jetpack_compose_all_in_one.ui.theme.spaceLarge
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(){

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    val menuList = listOf(
        Icons.Filled.Contacts to "Contacts",
        Icons.Filled.History to "History",
        Icons.Filled.Settings to "Settings"
    )

    var selectedItem by remember{ mutableStateOf(-1)}

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                menuList.forEachIndexed { index, pair ->
                    NavigationDrawerItem(
                        label = {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Icon(imageVector = pair.first, contentDescription = pair.second)
                                    Spacer(modifier = Modifier.width(15.dp))
                                    Text(text = pair.second)
                                }
                                },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index })
                }
            }
        }) {
        Scaffold(
            topBar = {
                TopAppBar{
                    scope.launch {
                        drawerState.open()
                    }
                }
            }
        ) {
            Box( modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center){
                Text(text = "This is current page")
            }
        }

    }
}

@Composable
fun DrawerHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spaceLarge),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header" , fontSize = sp_32)
    }
}