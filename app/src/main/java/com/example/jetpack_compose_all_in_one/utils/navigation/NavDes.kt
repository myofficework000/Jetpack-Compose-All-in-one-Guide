package com.example.jetpack_compose_all_in_one.utils.navigation

import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.utils.Constants.HOME
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_8_ANIMATIONS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_8_ANIMATIONS_ABOUT

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
    object Home : NavDes(NavigationDrawerData(HOME, HOME), R.string.app_name)
    object Internet : NavDes(NavigationDrawerData("internet", "Connectivity Manager"))
    object ForegroundService : NavDes(NavigationDrawerData("fService", "Foreground Services"))
    object BoundService : NavDes(NavigationDrawerData("bService", "Bound Services"))
    object Download : NavDes(NavigationDrawerData("download", "Download Manager"))
    object AlarmManager : NavDes(NavigationDrawerData("alarm", "Alarm Manager"))

    object Login1 : NavDes(NavigationDrawerData("login_style_1", "Login Page (Style 1)"))

    object Login2 : NavDes(NavigationDrawerData("login_style_2", "Login Page (Style 2)"))

    object QrCodeScanner : NavDes(NavigationDrawerData("qrcode_scanner", "QR Code Scanner"))

    object Tmdb : NavDes(NavigationDrawerData("tmdb", "TMDB API"))

    object Quotes : NavDes(NavigationDrawerData("quotes", "Quotes API"))

    object ChatDemoUI : NavDes(NavigationDrawerData("chatting_feature", "chatting feature"))

    object ShowImages : NavDes(NavigationDrawerData("img_show", "Show Device Images"))

    object L1Layouts : NavDes(NavigationDrawerData("l1", "Lesson 1: Row, Column, Box"))

    object L2Chapter1 : NavDes(NavigationDrawerData("l2_c1", "Chapter 1: Shapes"))

    object L2Chapter2 : NavDes(NavigationDrawerData("l2_c2", "Chapter 2: Text"))

    object L2Chapter3 : NavDes(NavigationDrawerData("l2_c3", "Chapter 3: Buttons"))

    object L2Chapter4 : NavDes(NavigationDrawerData("l2_c4", "Chapter 4: Image"))

    object L2Chapter5 : NavDes(NavigationDrawerData("l2_c5", "Chapter 5: Progressbar"))

    object L2Chapter6 : NavDes(NavigationDrawerData("l2_c6", "Chapter 6: Floating Action button"))

    object L3Chapter1 : NavDes(NavigationDrawerData("l3_c1", "Chapter 1: List Types"))

    object L4Chapter1 : NavDes(NavigationDrawerData("l4_c1", "Chapter 1: Dialogs"))

    object L5Chapter1 : NavDes(NavigationDrawerData("l5_c1", "Chapter 1: Map Basics"))

    object L5Chapter2 : NavDes(NavigationDrawerData("l5_c2", "Chapter 2: Map Types"))

    object L5Chapter3 : NavDes(NavigationDrawerData("l5_c3", "Chapter 3: Current Location on Map"))

    object L5Chapter4 : NavDes(NavigationDrawerData("l5_c4", "Chapter 4: Search on Map"))

    object L6Chapter1 : NavDes(NavigationDrawerData("l6_c1", "Theme change"))

    object L7Chapter1 : NavDes(NavigationDrawerData("l7_c1", "Constraint Layout Examples"))
    object Lesson8Animation : NavDes(NavigationDrawerData(LESSON_8_ANIMATIONS, LESSON_8_ANIMATIONS_ABOUT))

    object Lesson9ComposeTesting : NavDes(NavigationDrawerData(LESSON_8_ANIMATIONS, LESSON_8_ANIMATIONS_ABOUT))


    object QuoteSwipe : NavDes(NavigationDrawerData("quote2", "Swipe Quotes"))

    object NewsSample : NavDes(NavigationDrawerData("newsSample", "News Sample"))

    object WeatherSample : NavDes(NavigationDrawerData("weatherSample", "Weather Sample"))

    object DomainSearch : NavDes(NavigationDrawerData("domainSearch", "Domain Name Search"))

    object DogApi : NavDes(NavigationDrawerData("dogApi", "Dog API"))

    object RandomDogApi : NavDes(NavigationDrawerData("RandomDogApi", "Random Dog API"))

    object RandomFox : NavDes(NavigationDrawerData("RandomFoxApi", "Random Fox API"))

    object Maps : NavDes(NavigationDrawerData("maps", "Play with Maps"))

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
            listOf(
                Tmdb,
                Quotes,
                NewsSample,
                WeatherSample,
                DomainSearch,
                DogApi,
                RandomDogApi,
                RandomFox,
                Maps
            ),
            "API implementations"
        )
    )

    object L2Components : NavDes(
        NavigationCategoryData(
            listOf(L2Chapter1, L2Chapter2, L2Chapter3, L2Chapter4, L2Chapter5, L2Chapter6),
            "Lesson 2: Material Designs"
        )
    )

    object L3List : NavDes(
        NavigationCategoryData(
            listOf(L3Chapter1), "Lesson 3: List"
        )
    )

    object L4Dialogs : NavDes(
        NavigationCategoryData(
            listOf(L4Chapter1), "Lesson 4: Dialogs"
        )
    )

    object L5Maps : NavDes(
        NavigationCategoryData(
            listOf(L5Chapter1, L5Chapter2, L5Chapter3, L5Chapter4), "Lesson 5: Maps"
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
                L2Components,
                L3List,
                L4Dialogs,
                L5Maps,
                L6Chapter1,
                L7Chapter1,
                Lesson8Animation
            ), "Lessons"
        )
    )

    object CategoryFeatures : NavDes(
        NavigationCategoryData(
            listOf(
                ShowImages,
                CategoryServices,
                CategoryApis,
                Internet,
                Download,
                Login1,
                Login2,
                QrCodeScanner,
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
            Home,
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

        // The drawer's swipe gesture blocks all horizontal dragging interactions.
        // Add the page to the list so drawer can't swipe.
        fun disableDrawerSwiping(currentRoute: NavDes) = listOf(
            Maps,
            L5Chapter1,
            L5Chapter2,
            L5Chapter3,
            L5Chapter4
        ).contains(currentRoute)
    }
}