package com.example.jetpack_compose_all_in_one.lessons.lesson_21.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_all_in_one.lessons.lesson_21.DashboardScreen
import com.example.jetpack_compose_all_in_one.lessons.lesson_21.LoginScreen

@Composable
fun DataStoreDemoUI(){
    val navController = rememberNavController()
    
    NavHost(navController = navController,
        startDestination = Routes.LOGIN_SCREEN
    ){
        composable(route = Routes.LOGIN_SCREEN) { LoginScreen(navController = navController) }
        composable(route = Routes.DASHBOARD) { DashboardScreen() }
    }
}