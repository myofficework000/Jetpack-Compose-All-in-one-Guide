package com.example.jetpack_compose_all_in_one.ui.views.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_all_in_one.R
import kotlinx.coroutines.delay

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        // Main Screen
////        composable(Screen.Home.route) {
////            MainContainerOfApp(
////                internetViewModel,
////                {
////                    if (!isLocationAllowed(this)) it(null)
////                    else fusedLocationClient
////                        .getCurrentLocation(
////                            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
////                            locationCancelToken.token)
////                        .addOnCompleteListener { loc -> it(loc.result) }
////                },
////                {
////                    startForegroundService(
////                        playIntentForeground.putExtra(MusicForegroundService.name_arg, it.toString())
////                    )
////                },
////                { stopService( playIntentForeground ) },
////                { musicBoundService?.startMusic(it) },
////                { musicBoundService?.stopMusic() },
////                { musicBoundService?.pauseMusic(it) },
////                { musicBoundService?.resumeMusic() },
////                downloadObject,
////                { downloadObject.url = it }
////            )
//        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(Screen.Home.route)
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.jetpack_compose),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}
