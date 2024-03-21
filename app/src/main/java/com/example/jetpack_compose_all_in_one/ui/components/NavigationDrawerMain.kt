package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpack_compose_all_in_one.ui.theme.dp_16
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
import com.example.jetpack_compose_all_in_one.ui.theme.spaceLarge
import com.example.jetpack_compose_all_in_one.ui.views.theming.ThemeSettingsRow
import com.example.jetpack_compose_all_in_one.ui.views.theming.ThemeViewModel
import com.example.jetpack_compose_all_in_one.utils.navigation.NavDes
import com.example.jetpack_compose_all_in_one.utils.navigation.NavigationCategoryData
import com.example.jetpack_compose_all_in_one.utils.navigation.NavigationDrawerData

// content is most likely a Scaffold. So no padding needed.
@Composable
fun NavigationDrawerMain(
    navController: NavController,
    currentRoute: MutableState<NavDes>,
    drawerState: DrawerState,
    noSwiping: State<Boolean>,
    themeViewModel: ThemeViewModel,
    closeDrawerFunc: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                ScrollableColumn(
                    Modifier.weight(1f).padding(horizontal = dp_16)
                ) {
                    NavDes.drawerList.forEach {
                        DrawerCategoryAndItem(it) { des ->
                            des.data as NavigationDrawerData // This is for smart casting
                            navController.navigate(des.data.route)
                            currentRoute.value = des
                            closeDrawerFunc()
                        }
                    }
                }
                ThemeSettingsRow(vm = themeViewModel, Modifier.padding(16.dp))
            }
        },
        gesturesEnabled = if (noSwiping.value) drawerState.isOpen else true
    ) { content() }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DrawerCategoryAndItem(
    item: NavDes,
    onItemClick: (NavDes) -> Unit
) {
    if (item.data is NavigationCategoryData) {
        ExpandableContent({
            Text(
                item.data.displayName,
                Modifier.padding(ButtonDefaults.TextButtonContentPadding)
                    .basicMarquee(),
                MaterialTheme.colorScheme.onSurfaceVariant
            )
        }) {
            item.data.items.forEach {
                DrawerCategoryAndItem(it, onItemClick)
            }
        }
    } else {
        item.data as NavigationDrawerData // This is for smart casting
        Text(
            item.data.displayText,
            Modifier.padding(ButtonDefaults.TextButtonContentPadding)
                .fillMaxWidth()
                .basicMarquee()
                .clickable { onItemClick(item) },
            MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spaceLarge),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = sp_32)
    }
}