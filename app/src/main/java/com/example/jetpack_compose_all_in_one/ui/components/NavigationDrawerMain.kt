package com.example.jetpack_compose_all_in_one.ui.components

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
import com.example.jetpack_compose_all_in_one.utils.navigation.NavigationCategoryData
import com.example.jetpack_compose_all_in_one.utils.navigation.NavigationDrawerData

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
                    DrawerCategoryAndItem(it) { des ->
                        des.data as NavigationDrawerData // This is for smart casting
                        navController.navigate(des.data.route)
                        currentRoute.value = des.data.route
                        closeDrawerFunc()
                    }
                }
            }
        }
    ) { content() }
}

@Composable
private fun DrawerCategoryAndItem(
    item: NavDes,
    onItemClick: (NavDes) -> Unit
) {
    if (item.data is NavigationCategoryData) {
        ExpandableContent({
            Text(
                item.data.displayName,
                Modifier.padding(ButtonDefaults.TextButtonContentPadding),
                MaterialTheme.colorScheme.onSurfaceVariant
            )
        }) {
            item.data.items.forEach {
                DrawerCategoryAndItem(it, onItemClick) }
        }
    } else {
        item.data as NavigationDrawerData // This is for smart casting
        NavigationDrawerItem(
            selected = false,
            label = { Text(item.data.displayText) },
            icon = item.data.iconResId?.run{ { Icon(painterResource(this),"") } },
            onClick = { onItemClick(item) })
    }
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