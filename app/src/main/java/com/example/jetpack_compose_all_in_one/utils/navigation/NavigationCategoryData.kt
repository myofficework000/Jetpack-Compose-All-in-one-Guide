package com.example.jetpack_compose_all_in_one.utils.navigation

data class NavigationCategoryData(
    val items: List<NavDes>,
    val displayName: String = "",
    val iconResId: Int? = null
): INavigationDrawerItem
