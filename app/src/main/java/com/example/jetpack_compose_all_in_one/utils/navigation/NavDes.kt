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
sealed class NavDes(val data: INavigationDrawerItem, val customAppBarStringId: Int? = null) {

    // These are for individual items
    object Home : NavDes(NavigationDrawerData("home", "Home"), R.string.app_name)
    object Internet : NavDes(NavigationDrawerData("internet", "Connectivity Manager"))
    object ForegroundService : NavDes(NavigationDrawerData("fService", "Foreground Services"))
    object BoundService : NavDes(NavigationDrawerData("bService", "Bound Services"))
    object Download : NavDes(NavigationDrawerData("download", "Download Manager"))
    object AlarmManager : NavDes(NavigationDrawerData("alarm", "Alarm Manager"))

    object Login1 : NavDes(NavigationDrawerData("login_style_1", "Login Page (Style 1)"))

    object Login2 : NavDes(NavigationDrawerData("login_style_2", "Login Page (Style 2)"))

    object Tmdb : NavDes(NavigationDrawerData("tmdb", "TMDB API"))

    object Quotes : NavDes(NavigationDrawerData("quotes", "Quotes API"))

    object ChatDemoUI : NavDes(NavigationDrawerData("chatting_feature", "chatting feature"))

    object ShowImages : NavDes(NavigationDrawerData("img_show", "Show Device Images"))

    object L1Layouts : NavDes(NavigationDrawerData("l1", "Lesson 1: Row, Column, Box"))

    object L2Chapter1 : NavDes(NavigationDrawerData("chapter_1", "Chapter 1: Shapes"))

    object L2Chapter2 : NavDes(NavigationDrawerData("chapter_2", "Chapter 2: Text"))

    object L2Chapter3 : NavDes(NavigationDrawerData("chapter_3", "Chapter 3: Buttons"))

    object L2Chapter4 : NavDes(NavigationDrawerData("chapter_4", "Chapter 4: Image"))

    object QuoteSwipe : NavDes(NavigationDrawerData("quote2", "Swipe Quotes"))

    object NewsSample : NavDes(NavigationDrawerData("newsSample", "News Sample"))

    object WeatherSample : NavDes(NavigationDrawerData("weatherSample", "Weather Sample"))

    object DomainSearch : NavDes(NavigationDrawerData("domainSearch", "Domain Name Search"))

    object DogApi : NavDes(NavigationDrawerData("dogApi", "Dog API"))

    /*object Contacts: NavDes( NavigationDrawerData("contacts","Contacts",
        R.drawable.baseline_contacts_24
    ) )
    object History: NavDes( NavigationDrawerData("history","History",
        R.drawable.baseline_history_24
    ) )
    object Settings: NavDes( NavigationDrawerData("settings","Settings",
        R.drawable.baseline_settings_24
    ) )*/


    // These are for the categories
    object CategoryApis : NavDes(
        NavigationCategoryData(
            listOf(Tmdb, Quotes, NewsSample, WeatherSample, DomainSearch, DogApi),
            "API implementations"
        )
    )

    object L2Components : NavDes(
        NavigationCategoryData(
            listOf(L2Chapter1, L2Chapter2, L2Chapter3, L2Chapter4), "Lesson 2: Material Designs"
        )
    )

    object CategoryServices : NavDes(
        NavigationCategoryData(
            listOf(ForegroundService, BoundService, AlarmManager), "Service implementations"
        )
    )

    object CategoryLessons : NavDes(
        NavigationCategoryData(
            listOf(
                L1Layouts,
                L2Components
            ), "Lessons"
        )
    )

    object CategoryFeatures : NavDes(
        NavigationCategoryData(
            listOf(
                Home,
                ShowImages,
                CategoryServices,
                CategoryApis,
                Internet,
                Download,
                Login1,
                Login2,
                ChatDemoUI,
                QuoteSwipe
            ), "Features"
        )
    )

    // Some utils
    fun route() = (data as NavigationDrawerData).route
    fun displayText() = (data as NavigationDrawerData).displayText

    companion object {
        // This is a mix of individual destinations and categories,
        //      but for now only categories are needed.
        val drawerList = listOf(
            CategoryLessons,
            CategoryFeatures
        )

        val startDestination = Home

        // Put all pages that need a custom TopAppBar in this list.
        // It'll be evaluated in MainContainerOfApp.
        // Even if you only want the TopAppBar to disappear at certain states,
        //      you still need to recreate the whole bar, pass the necessary
        //      states from MainContainerOfApp and setup your own logic.
        fun needCustomAppBar(currentRoute: NavDes) = listOf(
            Login1
        ).contains(currentRoute)
    }
}