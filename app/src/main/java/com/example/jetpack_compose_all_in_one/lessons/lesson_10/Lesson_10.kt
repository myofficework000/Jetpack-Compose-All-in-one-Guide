package com.example.jetpack_compose_all_in_one.lessons.lesson_10

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.LessonText2
import com.example.jetpack_compose_all_in_one.ui.theme.twitterColor


@Preview
@Composable
fun Lesson_10() {
    LessonContent()
}

@Composable
fun LessonContent() {
    Text(text = "App Bars", modifier = Modifier.padding(8.dp))

    TopAppBarsDemo()
    BottomAppBarDemo()
    NavigationBarDemo()
    BottomAppBarDemo2()
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun BottomAppBarDemo2() {
    val navController = rememberNavController()
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = { TopAppBarsDemo() },
        bottomBar = { MyBottomAppBar(navController) }) {
        NavHost(
            navController,
            startDestination = BottomNavScreen.Screen1.route,
            modifier = Modifier.padding(it)
        ) {
            composable(BottomNavScreen.Screen1.route) {
                it.arguments?.getString("ok")
                HomeContent(scrollState)
            }
            composable(BottomNavScreen.Screen2.route) {
                it.arguments?.putString("ok", "hello")

                ProfileContent(it.arguments?.getString("ok"))
            }
            composable(BottomNavScreen.Screen3.route) {
                SettingContent()
            }
        }
    }
}

@Composable
fun MyBottomAppBar(navController: NavHostController) {
    NavigationBar(Modifier.fillMaxWidth(), containerColor = Color.Green) {
        NavigationBarItem(
            label = { Text(text = BottomNavScreen.Screen1.route) },
            selected = false,

            onClick = { navController.navigate(BottomNavScreen.Screen1.route) },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            })
        NavigationBarItem(
            label = { Text(text = BottomNavScreen.Screen1.route) },
            selected = false,
            onClick = { navController.navigate(BottomNavScreen.Screen2.route) },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            })
        NavigationBarItem(
            label = { Text(BottomNavScreen.Screen3.route) },
            selected = false,
            onClick = { navController.navigate(BottomNavScreen.Screen3.route) },
            icon = {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            })
    }
}

sealed class BottomNavScreen(val route: String) {
    object Screen1 : BottomNavScreen("Home")
    object Screen2 : BottomNavScreen("Profile")
    object Screen3 : BottomNavScreen("Setting")
}


@Composable
fun HomeContent(scrollState: LazyListState) {

    val list = (0..50).toList()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState

        ) {
            items(list) {
                Text(text = it.toString(), fontSize = 30.sp, fontStyle = FontStyle.Italic)
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Home", fontSize = 25.sp, modifier = Modifier.background(Color.Yellow))

        }
    }
}

@Composable
fun SettingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Setting", fontSize = 25.sp, modifier = Modifier.background(Color.Blue))
        }
    }
}

@Composable
fun ProfileContent(string: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Profile $string",
                fontSize = 25.sp,
                modifier = Modifier.background(Color.Cyan)
            )
        }
    }
}