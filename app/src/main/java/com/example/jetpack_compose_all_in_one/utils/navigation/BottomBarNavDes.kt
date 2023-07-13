package com.example.jetpack_compose_all_in_one.utils.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.ACCOUNT
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SEARCH
import com.example.jetpack_compose_all_in_one.utils.navigation.NavConstants.SETTINGS

sealed class BottomBarNavDes(val data: BottomNavItem){
    object Settings: BottomBarNavDes(BottomNavItem(R.string.settings, SETTINGS,Icons.Filled.Settings))
    object Account: BottomBarNavDes(BottomNavItem(R.string.account, ACCOUNT,Icons.Filled.Person))
    object Search: BottomBarNavDes(BottomNavItem(R.string.search, SEARCH,Icons.Filled.Search))
}

