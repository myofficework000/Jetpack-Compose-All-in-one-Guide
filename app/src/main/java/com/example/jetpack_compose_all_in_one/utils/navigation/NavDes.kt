package com.example.jetpack_compose_all_in_one.utils.navigation

import com.example.jetpack_compose_all_in_one.R

/*
    How to add an item in Navigation Drawer:
    1. Create a sealed class object below. Check all these examples for reference
    2. Add the created object in drawerList. The order matters.
    3. If you want your own TopAppBar (Navigation Drawer is not configurable),
            also add the object into the list of needCustomAppBar.
    4. Inside the NavHost (Currently positioned in MainContainerOfApp), add a
            composable item. Again, take reference from the existing items above.
 */
sealed class NavDes(val data: NavigationDrawerData) {
    object Home: NavDes( NavigationDrawerData("home","Home") )
    object Internet: NavDes( NavigationDrawerData("internet","Connectivity Manager") )
    object ForegroundService: NavDes( NavigationDrawerData("fService","Foreground Services") )
    object BoundService: NavDes( NavigationDrawerData("bService","Bound Services") )
    object Download: NavDes( NavigationDrawerData("download","Download Manager") )
    object AlarmManager: NavDes( NavigationDrawerData("alarm","Alarm Manager") )

    object Login1: NavDes( NavigationDrawerData("login_style_1","Login Page (Style 1)") )

    object Tmdb: NavDes( NavigationDrawerData("tmdb","TMDB API") )

    object Quotes: NavDes( NavigationDrawerData("quotes", "Quotes API") )

    object ChatDemoUI: NavDes( NavigationDrawerData("chatting_feature", "chatting feature") )

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
            Login1,
            Tmdb,
            Quotes,
            ChatDemoUI
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