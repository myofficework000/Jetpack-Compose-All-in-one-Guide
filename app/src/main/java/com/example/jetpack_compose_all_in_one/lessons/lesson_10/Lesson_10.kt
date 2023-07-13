package com.example.jetpack_compose_all_in_one.lessons.lesson_10

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.CleanHands
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.ReadMore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.LessonText2
import com.example.jetpack_compose_all_in_one.ui.theme.VioletA100
import com.example.jetpack_compose_all_in_one.ui.theme.sp_20
import com.example.jetpack_compose_all_in_one.ui.theme.twitterColor
import com.example.jetpack_compose_all_in_one.utils.navigation.BottomBarNavDes


@Preview
@Composable
fun Lesson_10() {
    LessonContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LessonContent() {
    val navController  = rememberNavController()

    Text(text = "App Bars", modifier = Modifier.padding(8.dp))

    TopAppBarsDemo()
    Scaffold(
        topBar = { TopAppBarsDemo() },
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = { paddingUnit ->
        NavigationGraph(
            modifier = Modifier.padding(paddingUnit),
            navController = navController)
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarsDemo() {
    LessonHeader("Top App bar")

    SmallTopAppBar(
        title = { Text(text = "Home") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TopAppBar(
        title = { Text(text = "Instagram") },
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        elevation = 8.dp,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.ic_send), contentDescription = null)
            }
        }
    )

    Spacer(modifier = Modifier.height(8.dp))

    TopAppBar(
        title = {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = null,
                tint = twitterColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        elevation = 8.dp,
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .size(32.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val bottomNavItems = listOf(
        BottomBarNavDes.Settings,
        BottomBarNavDes.Search,
        BottomBarNavDes.Account
    )

    BottomNavigation(
        backgroundColor = VioletA100,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach{ item ->
            BottomNavigationItem(
                icon = {Icon(item.data.icon, contentDescription = "")},
                label = { Text(text = stringResource(id = item.data.name))},
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.data.route,
                onClick = {
                    navController.navigate(item.data.route)
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(modifier: Modifier,navController: NavHostController){
    NavHost(
        modifier = modifier.fillMaxWidth(),
        navController = navController, startDestination = BottomBarNavDes.Settings.data.route ){
        composable(BottomBarNavDes.Settings.data.route){
            SettingsScreen()
        }
        composable(BottomBarNavDes.Search.data.route){
            SearchScreen2()
        }
        composable(BottomBarNavDes.Account.data.route){
            AccountScreen()
        }

    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = "Settings", fontWeight = FontWeight.Bold, fontSize = sp_20)
    }
}

@Composable
fun SearchScreen2() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = "Search", fontWeight = FontWeight.Bold, fontSize = sp_20)
    }
}

@Composable
fun AccountScreen() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = "Account", fontWeight = FontWeight.Bold, fontSize = sp_20)
    }
}

@Composable
fun BottomAppBarDemo() {
    Spacer(modifier = Modifier.height(16.dp))
    LessonText2("Bottom app bars: Note bottom app bar support FAB cutouts when used with scafolds see demoUI crypto app")

    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.MoreHoriz, contentDescription = null)
        }
        LessonText2("Bottom App Bar")
    }
}

@Composable
fun NavigationBarDemo() {
    Spacer(modifier = Modifier.height(16.dp))
    LessonText2("Bottom Navigation Bars")
    val spotifyNavItemState = remember { mutableStateOf(false) }
    BottomNavigation(backgroundColor = MaterialTheme.colorScheme.surface) {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
            selected = spotifyNavItemState.value,
            onClick = { spotifyNavItemState.value = true },
            label = { Text(text = stringResource(id = R.string.home)) },
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            selected = spotifyNavItemState.value,
            onClick = { spotifyNavItemState.value = true },
            label = { Text(text = stringResource(id = R.string.search)) }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.LibraryMusic, contentDescription = null) },
            selected = spotifyNavItemState.value,
            onClick = { spotifyNavItemState.value = true },
            label = { Text(text = stringResource(id = R.string.music)) }
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.ReadMore, contentDescription = null) },
            selected = spotifyNavItemState.value,
            onClick = { spotifyNavItemState.value = true }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
            selected = spotifyNavItemState.value,
            onClick = { spotifyNavItemState.value = true }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Outlined.CleanHands, contentDescription = null) },
            selected = spotifyNavItemState.value,
            onClick = { spotifyNavItemState.value = true }
        )
    }
}
