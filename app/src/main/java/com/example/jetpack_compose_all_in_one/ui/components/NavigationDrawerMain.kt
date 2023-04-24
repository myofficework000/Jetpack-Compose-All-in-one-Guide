package com.example.jetpack_compose_all_in_one.ui.views.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
import com.example.jetpack_compose_all_in_one.ui.theme.spaceLarge
import com.example.jetpack_compose_all_in_one.utils.navigation.NavDes

// content is most likely a Scaffold. So no padding needed.
@Composable
fun NavigationDrawerMain(
    navController: NavController,
    currentRoute: MutableState<String>,
    drawerState: DrawerState,
    closeDrawerFunc: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                NavDes.drawerList.forEach {
                    NavigationDrawerItem(
                        selected = false,
                        label = { Text(it.data.displayText) },
                        icon = it.data.iconResId?.run{ { Icon(painterResource(this),"") } },
                        onClick = {
                            navController.navigate(it.data.route)
                            currentRoute.value = it.data.route
                            closeDrawerFunc()
                        })
                }
            }
        }
    ) { content() }
}

@Composable
private fun DrawerHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spaceLarge),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header" , fontSize = sp_32)
    }
}