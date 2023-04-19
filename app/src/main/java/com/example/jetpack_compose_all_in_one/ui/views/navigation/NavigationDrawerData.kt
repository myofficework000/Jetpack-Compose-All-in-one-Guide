package com.example.jetpack_compose_all_in_one.ui.views.navigation

data class NavigationDrawerData(
    val route: String,
    val displayText: String = route,
    val iconResId: Int? = null,
    val additionalFuncOnClick: () -> Unit = {}
)