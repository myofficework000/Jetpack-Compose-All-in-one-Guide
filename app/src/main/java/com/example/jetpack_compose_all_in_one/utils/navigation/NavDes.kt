package com.example.jetpack_compose_all_in_one.utils.navigation

import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ACTIVITY
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ACTIVITY_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.AIRTEL_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.AIRTEL_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ALARM_MANAGER
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ALARM_MANAGER_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ANDROID_ARCHITECTURES
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ANDROID_JETPACK
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.API_IMPLEMENTATIONS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.APPLICATION_COMPONENTS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.BOUND_SERVICE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.BOUND_SERVICE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.BROADCAST_RECEIVERS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.BROADCAST_RECEIVERS_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CHATGPT_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CHATGPT_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CHAT_DEMO_UI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CHAT_DEMO_UI_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CLEAN_CODE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CLEAN_CODE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CLEAN_CODE_ABOUT_WITH_MVI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CLEAN_CODE_WITH_MVI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.COLLAPSABLE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.COLLAPSABLE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CONTENT_PROVIDER
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CONTENT_PROVIDER_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CURRENCY_EXCHANGE_API
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CURRENCY_EXCHANGE_API_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CurrencyConverterUI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.CurrencyConverterUI_About
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.DEMO_UI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.DOG_API
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.DOG_API_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.DOMAIN_SEARCH
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.DOMAIN_SEARCH_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.DOWNLOAD
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.DOWNLOAD_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.FEATURES
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.FLOW_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.FLOW_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.FOREGROUND_SERVICE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.FOREGROUND_SERVICE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.FacebookUI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.FacebookUI_About
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.GITHUB_PAGING_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.GITHUB_PAGING_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.GRAPHQL_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.GRAPHQL_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.HOME
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.HOME_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.HistoryOfDayUI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.HistoryOfDayUI_About
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.INTERNET
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.INTERNET_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSONS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_10_APP_BARS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_10_APP_BARS_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_11_INTEROPERABILITY
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_11_INTEROPERABILITY_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_12_WEBVIEW
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_12_WEBVIEW_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_13_LOCALIAZATION
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_13_LOCALIAZATION_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_14_DROP_DOWN_MENU
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_14_DROP_DOWN_MENU_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_1_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_1_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_2
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_2_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_3
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_3_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_4
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_4_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_5
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_5_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_6
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_6_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_7
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_7_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_8
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_8_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_9
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_CHAPTER_9_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_2_DESC
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_3_CHAPTER_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_3_CHAPTER_1_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_4_CHAPTER_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_4_CHAPTER_1_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_1_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_2
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_2_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_3
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_3_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_4
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_CHAPTER_4_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_5_DESC
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_6_CHAPTER_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_6_CHAPTER_1_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_7_CHAPTER_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_7_CHAPTER_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_8_ANIMATIONS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_8_ANIMATIONS_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_9_UI_TESTING
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LESSON_9_UI_TESTING_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LIVE_DATA_AND_VIEW_MODEL
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LIVE_DATA_AND_VIEW_MODEL_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LOGIN_1
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LOGIN_1_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LOGIN_2
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.LOGIN_2_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MAPS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MAPS_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MVI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MVI_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MVP
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MVP_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MVVM
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.MVVM_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NEWS_API_HEADLINE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NEWS_API_HEADLINE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NEWS_SAMPLE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NEWS_SAMPLE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NOTES_ROOM_DB
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NOTES_ROOM_DB_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NewsUI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.NewsUI_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.PAGING_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.PAGING_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.PASSWORD_VALIDATION
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.PASSWORD_VALIDATION_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.PROFILE_UPDATE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.PROFILE_UPDATE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.QRCODE_SCANNER
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.QRCODE_SCANNER_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.QUOTES
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.QUOTES_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.QUOTE_2
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.QUOTE_2_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.RANDOM_DOG_API
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.RANDOM_DOG_API_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.RANDOM_FOX_API
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.RANDOM_FOX_API_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ROOM_DATABASE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ROOM_DATABASE_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.RXJAVA_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.RXJAVA_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SERVICE_IMPLEMENTATIONS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SHARED_PREF_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SHARED_PREF_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SHOW_CONTACTS
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SHOW_CONTACTS_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SHOW_IMAGES
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SHOW_IMAGES_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.STRIPE_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.STRIPE_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SpaceXGraphQL_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SpaceXGraphQL_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.THIRD_PARTY
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.TIMER_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.TIMER_DEMO_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.TMDB
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.TMDB_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.TiKTokUI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.TiKTokUI_About
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.WEATHER_SAMPLE
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.WEATHER_SAMPLE_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.WORK_MANAGER_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.WORK_MANAGER_DEMO
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.YELP_API
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.YELP_API_ABOUT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.YoutubeUI
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.YoutubeUI_ABOUT

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
    object Home : NavDes(NavigationDrawerData(HOME, HOME_ABOUT), R.string.app_name)
    object Internet : NavDes(NavigationDrawerData(INTERNET, INTERNET_ABOUT))
    object ForegroundService :
        NavDes(NavigationDrawerData(FOREGROUND_SERVICE, FOREGROUND_SERVICE_ABOUT))

    object BoundService : NavDes(NavigationDrawerData(BOUND_SERVICE, BOUND_SERVICE_ABOUT))
    object Download : NavDes(NavigationDrawerData(DOWNLOAD, DOWNLOAD_ABOUT))
    object AlarmManager : NavDes(NavigationDrawerData(ALARM_MANAGER, ALARM_MANAGER_ABOUT))

    object Login1 : NavDes(NavigationDrawerData(LOGIN_1, LOGIN_1_ABOUT))

    object Login2 : NavDes(NavigationDrawerData(LOGIN_2, LOGIN_2_ABOUT))

    object QrCodeScanner : NavDes(NavigationDrawerData(QRCODE_SCANNER, QRCODE_SCANNER_ABOUT))

    object Tmdb : NavDes(NavigationDrawerData(TMDB, TMDB_ABOUT))

    object Quotes : NavDes(NavigationDrawerData(QUOTES, QUOTES_ABOUT))

    object ChatDemoUI : NavDes(NavigationDrawerData(CHAT_DEMO_UI, CHAT_DEMO_UI_ABOUT))

    object ShowImages : NavDes(NavigationDrawerData(SHOW_IMAGES, SHOW_IMAGES_ABOUT))

    object ShowContacts : NavDes(NavigationDrawerData(SHOW_CONTACTS, SHOW_CONTACTS_ABOUT))

    object TimerDemo : NavDes(NavigationDrawerData(TIMER_DEMO, TIMER_DEMO_ABOUT))

    object L1Layouts : NavDes(NavigationDrawerData(LESSON_1, LESSON_1_ABOUT))

    object L2Chapter1 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_1, LESSON_2_CHAPTER_1_ABOUT))

    object L2Chapter2 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_2, LESSON_2_CHAPTER_2_ABOUT))

    object L2Chapter3 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_3, LESSON_2_CHAPTER_3_ABOUT))

    object L2Chapter4 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_4, LESSON_2_CHAPTER_4_ABOUT))

    object L2Chapter5 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_5, LESSON_2_CHAPTER_5_ABOUT))

    object L2Chapter6 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_6, LESSON_2_CHAPTER_6_ABOUT))

    object L2Chapter7 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_7, LESSON_2_CHAPTER_7_ABOUT))

    object L2Chapter8 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_8, LESSON_2_CHAPTER_8_ABOUT))
    object L2Chapter9 : NavDes(NavigationDrawerData(LESSON_2_CHAPTER_9, LESSON_2_CHAPTER_9_ABOUT))


    object L3List : NavDes(NavigationDrawerData(LESSON_3_CHAPTER_1, LESSON_3_CHAPTER_1_ABOUT))

    object L4Dialogs : NavDes(NavigationDrawerData(LESSON_4_CHAPTER_1, LESSON_4_CHAPTER_1_ABOUT))

    object L5Chapter1 : NavDes(NavigationDrawerData(LESSON_5_CHAPTER_1, LESSON_5_CHAPTER_1_ABOUT))

    object L5Chapter2 : NavDes(NavigationDrawerData(LESSON_5_CHAPTER_2, LESSON_5_CHAPTER_2_ABOUT))

    object L5Chapter3 : NavDes(NavigationDrawerData(LESSON_5_CHAPTER_3, LESSON_5_CHAPTER_3_ABOUT))

    object L5Chapter4 : NavDes(NavigationDrawerData(LESSON_5_CHAPTER_4, LESSON_5_CHAPTER_4_ABOUT))

    object L6Chapter1 : NavDes(NavigationDrawerData(LESSON_6_CHAPTER_1, LESSON_6_CHAPTER_1_ABOUT))

    object L7Chapter1 : NavDes(NavigationDrawerData(LESSON_7_CHAPTER_1, LESSON_7_CHAPTER_ABOUT))
    object Lesson8Animation :
        NavDes(NavigationDrawerData(LESSON_8_ANIMATIONS, LESSON_8_ANIMATIONS_ABOUT))

    object Lesson9ComposeTesting :
        NavDes(NavigationDrawerData(LESSON_9_UI_TESTING, LESSON_9_UI_TESTING_ABOUT))

    object Lesson10AppBars :
        NavDes(NavigationDrawerData(LESSON_10_APP_BARS, LESSON_10_APP_BARS_ABOUT))

    object Lesson11Interoperability :
        NavDes(NavigationDrawerData(LESSON_11_INTEROPERABILITY, LESSON_11_INTEROPERABILITY_ABOUT))

    object Lesson12WebView :
        NavDes(NavigationDrawerData(LESSON_12_WEBVIEW, LESSON_12_WEBVIEW_ABOUT))

    object Lesson13Localization :
        NavDes(NavigationDrawerData(LESSON_13_LOCALIAZATION, LESSON_13_LOCALIAZATION_ABOUT))

    object Lesson14DropDownMenu :
        NavDes(NavigationDrawerData(LESSON_14_DROP_DOWN_MENU, LESSON_14_DROP_DOWN_MENU_ABOUT))

    object QuoteSwipe : NavDes(NavigationDrawerData(QUOTE_2, QUOTE_2_ABOUT))

    object NewsSample : NavDes(NavigationDrawerData(NEWS_SAMPLE, NEWS_SAMPLE_ABOUT))

    object NewsApiHeadline :
        NavDes(NavigationDrawerData(NEWS_API_HEADLINE, NEWS_API_HEADLINE_ABOUT))

    object WeatherSample : NavDes(NavigationDrawerData(WEATHER_SAMPLE, WEATHER_SAMPLE_ABOUT))

    object DomainSearch : NavDes(NavigationDrawerData(DOMAIN_SEARCH, DOMAIN_SEARCH_ABOUT))

    object DogApi : NavDes(NavigationDrawerData(DOG_API, DOG_API_ABOUT))

    object RandomDogApi : NavDes(NavigationDrawerData(RANDOM_DOG_API, RANDOM_DOG_API_ABOUT))

    object YelpApi : NavDes(NavigationDrawerData(YELP_API, YELP_API_ABOUT))

    object CurrencyExchangeApi :
        NavDes(NavigationDrawerData(CURRENCY_EXCHANGE_API, CURRENCY_EXCHANGE_API_ABOUT))

    object RandomFox : NavDes(NavigationDrawerData(RANDOM_FOX_API, RANDOM_FOX_API_ABOUT))

    object Maps : NavDes(NavigationDrawerData(MAPS, MAPS_ABOUT))

    object FlowDemo : NavDes(NavigationDrawerData(FLOW_DEMO, FLOW_DEMO_ABOUT))

    object Collapsable : NavDes(NavigationDrawerData(COLLAPSABLE, COLLAPSABLE_ABOUT))

    object NoteRoomDemo : NavDes(NavigationDrawerData(NOTES_ROOM_DB, NOTES_ROOM_DB_ABOUT))

    object ProfileUpdate : NavDes(NavigationDrawerData(PROFILE_UPDATE, PROFILE_UPDATE_ABOUT))

    object SharedPrefDemo : NavDes(NavigationDrawerData(SHARED_PREF_DEMO, SHARED_PREF_DEMO_ABOUT))

    object StripeDemo : NavDes(NavigationDrawerData(STRIPE_DEMO, STRIPE_DEMO_ABOUT))

    object ChatGPTDemo : NavDes(NavigationDrawerData(CHATGPT_DEMO, CHATGPT_DEMO_ABOUT))

    object GithubPagingDemo :
        NavDes(NavigationDrawerData(GITHUB_PAGING_DEMO, GITHUB_PAGING_DEMO_ABOUT))

    object AirtelDemo : NavDes(NavigationDrawerData(AIRTEL_DEMO, AIRTEL_DEMO_ABOUT))

    object SpaceXGraphQLDemo :
        NavDes(NavigationDrawerData(SpaceXGraphQL_DEMO, SpaceXGraphQL_DEMO_ABOUT))

    object PasswordValidation :
        NavDes(NavigationDrawerData(PASSWORD_VALIDATION, PASSWORD_VALIDATION_ABOUT))

    object RxJavaDemo : NavDes(NavigationDrawerData(RXJAVA_DEMO, RXJAVA_DEMO_ABOUT))

    object GraphqlDemo: NavDes(NavigationDrawerData(GRAPHQL_DEMO, GRAPHQL_DEMO_ABOUT))


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
                NewsApiHeadline,
                WeatherSample,
                DomainSearch,
                DogApi,
                RandomDogApi,
                RandomFox,
                Maps
            ),
            API_IMPLEMENTATIONS
        )
    )

    object L2Components : NavDes(
        NavigationCategoryData(
            listOf(
                L2Chapter1,
                L2Chapter2,
                L2Chapter3,
                L2Chapter4,
                L2Chapter5,
                L2Chapter6,
                L2Chapter7,
                L2Chapter8,
                L2Chapter9
            ),
            LESSON_2_DESC
        )
    )

    object L5Maps : NavDes(
        NavigationCategoryData(
            listOf(L5Chapter1, L5Chapter2, L5Chapter3, L5Chapter4), LESSON_5_DESC
        )
    )

    object CategoryServices : NavDes(
        NavigationCategoryData(
            listOf(ForegroundService, BoundService, AlarmManager), SERVICE_IMPLEMENTATIONS
        )
    )

    object ContentProvider : NavDes(NavigationDrawerData(CONTENT_PROVIDER, CONTENT_PROVIDER_ABOUT))
    object BroadCastReceiver :
        NavDes(NavigationDrawerData(BROADCAST_RECEIVERS, BROADCAST_RECEIVERS_ABOUT))

    object Activity : NavDes(NavigationDrawerData(ACTIVITY, ACTIVITY_ABOUT))

    object ApplicationComponents : NavDes(
        NavigationCategoryData(
            listOf(
                Activity,
                BroadCastReceiver,
                ContentProvider,
                CategoryServices
            ), APPLICATION_COMPONENTS
        )
    )

    object Mvp : NavDes(NavigationDrawerData(MVP, MVP_ABOUT))
    object Mvvm : NavDes(NavigationDrawerData(MVVM, MVVM_ABOUT))
    object Mvi : NavDes(NavigationDrawerData(MVI, MVI_ABOUT))
    object CleanCode : NavDes(NavigationDrawerData(CLEAN_CODE, CLEAN_CODE_ABOUT))
    object CleanCodeWithMVI :
        NavDes(NavigationDrawerData(CLEAN_CODE_WITH_MVI, CLEAN_CODE_ABOUT_WITH_MVI))

    object tikTok : NavDes(NavigationDrawerData(TiKTokUI, TiKTokUI_About))
    object currencyConverter : NavDes(NavigationDrawerData(CurrencyConverterUI, CurrencyConverterUI_About))
    object youTube : NavDes(NavigationDrawerData(YoutubeUI, YoutubeUI_ABOUT))
    object news : NavDes(NavigationDrawerData(NewsUI, NewsUI_ABOUT))
    object faceBook : NavDes(NavigationDrawerData(FacebookUI, FacebookUI_About))
    object historyOfDay : NavDes(NavigationDrawerData(HistoryOfDayUI, HistoryOfDayUI_About))


    object AndroidArchitectures : NavDes(
        NavigationCategoryData(
            listOf(
                Mvp,
                Mvvm,
                Mvi,
                CleanCode,
                CleanCodeWithMVI
            ), ANDROID_ARCHITECTURES
        )
    )

    object WorkManagerDemo : NavDes(NavigationDrawerData(WORK_MANAGER_DEMO, WORK_MANAGER_ABOUT))

    object LiveDataAndViewModel :
        NavDes(NavigationDrawerData(LIVE_DATA_AND_VIEW_MODEL, LIVE_DATA_AND_VIEW_MODEL_ABOUT))

    object RoomDatabaseDemo : NavDes(NavigationDrawerData(ROOM_DATABASE_DEMO, ROOM_DATABASE_ABOUT))

    object Paging : NavDes(NavigationDrawerData(PAGING_DEMO, PAGING_ABOUT))

    object AndroidJetpack : NavDes(
        NavigationCategoryData(
            listOf(
                WorkManagerDemo,
                LiveDataAndViewModel,
                RoomDatabaseDemo,
                Paging
            ), ANDROID_JETPACK
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
                Lesson8Animation,
                Lesson9ComposeTesting,
                Lesson10AppBars,
                Lesson11Interoperability,
                Lesson12WebView,
                Lesson13Localization,
                Lesson14DropDownMenu
            ), LESSONS
        )
    )

    object CategoryFeatures : NavDes(
        NavigationCategoryData(
            listOf(
                ShowImages,
                ShowContacts,
                CategoryServices,
                CategoryApis,
                Internet,
                Download,
                Login1,
                Login2,
                QrCodeScanner,
                ChatDemoUI,
                QuoteSwipe,
                FlowDemo,
                Collapsable,
                TimerDemo,
                NoteRoomDemo,
                ProfileUpdate,
                SharedPrefDemo,
                PasswordValidation

            ), FEATURES
        )
    )

    object CategoryThirdParty : NavDes(
        NavigationCategoryData(
            listOf(
                StripeDemo,
                ChatGPTDemo,
                GithubPagingDemo,
                YelpApi,
                CurrencyExchangeApi,
                AirtelDemo,
                SpaceXGraphQLDemo,
                RxJavaDemo,
                GraphqlDemo
            ), THIRD_PARTY
        )
    )

    object DemoUI : NavDes(
        NavigationCategoryData(
            listOf(
                youTube,
                faceBook,
                tikTok,
                currencyConverter,
                historyOfDay,
                news
            ), DEMO_UI
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
            ApplicationComponents,
            AndroidArchitectures,
            AndroidJetpack,
            CategoryThirdParty,
            CategoryFeatures,
            DemoUI
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