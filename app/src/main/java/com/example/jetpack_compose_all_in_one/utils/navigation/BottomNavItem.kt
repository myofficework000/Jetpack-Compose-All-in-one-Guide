package com.example.jetpack_compose_all_in_one.utils.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    @StringRes val name: Int,
    val route: String,
    val icon: ImageVector
)
