package com.example.jetpack_compose_all_in_one.ui.views.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.ui.res.painterResource
import com.example.jetpack_compose_all_in_one.R

sealed class NavDes(val data: NavigationDrawerData) {
    object Home: NavDes( NavigationDrawerData("home","Home") )
    object Internet: NavDes( NavigationDrawerData("internet","Connectivity Manager") )
    object ForegroundService: NavDes( NavigationDrawerData("fService","Foreground Services") )
    object BoundService: NavDes( NavigationDrawerData("bService","Bound Services") )
    object Download: NavDes( NavigationDrawerData("download","Download Manager") )
    object AlarmManager: NavDes( NavigationDrawerData("alarm","Alarm Manager") )

    object Login1: NavDes( NavigationDrawerData("login_style_1","Login Page (Style 1)") )


    object Contacts: NavDes( NavigationDrawerData("contacts","Contacts",
        R.drawable.baseline_contacts_24
    ) )
    object History: NavDes( NavigationDrawerData("history","History",
        R.drawable.baseline_history_24
    ) )
    object Settings: NavDes( NavigationDrawerData("settings","Settings",
    R.drawable.baseline_settings_24
    ) )

    companion object {
        val drawerList = listOf(
            Home,
            Internet,
            ForegroundService,
            BoundService,
            Download,
            AlarmManager,
            Login1
        )

        // Put all pages that need a custom TopAppBar in this list.
        // It'll be evaluated in MainContainerOfApp.
        // Even if you only want the TopAppBar to disappear at certain states,
        //      you still need to recreate the whole bar, pass the necessary
        //      states from MainContainerOfApp and setup your own logic.
        // If that's too inconvenient, give me some time to redesign this setup.
        fun needCustomAppBar(currentRoute: String) = listOf(
            Login1
        ).map { it.data.route }.contains(currentRoute)
    }
}