package com.example.jetpack_compose_all_in_one.features.login_style_1

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.DrawerButton
import com.example.jetpack_compose_all_in_one.ui.views.sign_in_sign_up.RegistrationForm
import com.example.jetpack_compose_all_in_one.ui.components.OutlinedButton
import com.example.jetpack_compose_all_in_one.ui.components.ScrollableColumn
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.components.TextButtonWithIcon
import com.example.jetpack_compose_all_in_one.ui.theme.LightBlueToBlue30
import com.example.jetpack_compose_all_in_one.ui.views.sign_in_sign_up.LoginForm1

@Composable
fun LoginPage(
    drawerState: DrawerState
) {
    var displayMode by remember{ mutableStateOf("") }

    when (displayMode) {
        "login" -> {
            LoginFormLogin(
                banner = painterResource(R.drawable.placeholder_banner_2),
                onBack = { displayMode = "" },
                onGotoRegister = { displayMode = "register" }
            ) {_,_,_->}
        }
        "register" -> {
            LoginFormRegister(
                banner = painterResource(R.drawable.placeholder_banner_3),
                onBack = { displayMode = "" },
                onGotoLogin = { displayMode = "login" }
            ) {}
        }
        else -> {
            LoginMainMenu(
                banner = painterResource(R.drawable.placeholder_banner_1),
                drawerState
            ) {
                displayMode = it
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginMainMenu(
    banner: Painter? = null,
    drawerState: DrawerState,
    onPageChange: (String) -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                navigationIcon = { DrawerButton(drawerState,scope) }
            )
        },
        modifier = Modifier.background(LightBlueToBlue30),
        containerColor = Color.Transparent
    ) {
        Box(
            // It doesn't use padding so the back button can overlap the content.
            Modifier
                .fillMaxSize()
                .padding(it), contentAlignment = Alignment.Center) {
            Column(
                Modifier.fillMaxSize()
            ) {
                banner?.run{
                    Image(this,"",
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Column(
                    Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Touch Grass Groceries", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text("Grocery store at your doorsteps", color = Color.White, fontSize = 20.sp)
                    Spacer(Modifier.weight(1f))
                    SimpleTextButton("Login", Modifier.fillMaxWidth()) {
                        onPageChange("login")
                    }
                    OutlinedButton("Register", Modifier.fillMaxWidth()) {
                        onPageChange("register")
                    }
                }
            }
        }
    }
}

@Composable
fun LoginFormLogin(
    banner: Painter? = null,
    onBack: () -> Unit,
    onGotoRegister: () -> Unit,
    onLogin: (String, String, Boolean) -> Unit
) {
    BackHandler { onBack() }

    Scaffold(
        Modifier.background(LightBlueToBlue30),
        containerColor = Color.Transparent
    ) {
        TextButtonWithIcon(
            R.drawable.baseline_arrow_back_24,
            "Back",
            Modifier.zIndex(1f),
            textColor = Color.Black
        ) { onBack() }
        Column(Modifier.fillMaxSize()) {
            banner?.run{
                Image(this,"",
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.25f),
                    contentScale = ContentScale.FillWidth
                )
            }

            LoginForm1(
                onGotoRegister = onGotoRegister,
                onLogin = onLogin
            )
        }
    }
}

@Composable
fun LoginFormRegister(
    banner: Painter? = null,
    onBack: () -> Unit,
    onGotoLogin: () -> Unit,
    onRegister: () -> Unit
) {
    BackHandler { onBack() }

    Scaffold(
        Modifier.background(LightBlueToBlue30),
        containerColor = Color.Transparent
    ) {
        TextButtonWithIcon(
            R.drawable.baseline_arrow_back_24,
            "Back",
            Modifier.zIndex(1f),
            textColor = Color.Black
        ) { onBack() }
        ScrollableColumn(Modifier.fillMaxSize()) {
            banner?.run{
                Image(this,"",
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    contentScale = ContentScale.FillWidth
                )
            }
            RegistrationForm {

            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginPagePreview() {
    LoginPage(
        DrawerState(DrawerValue.Closed)
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginMainMenuPreview() {
    LoginMainMenu(
        banner = painterResource(R.drawable.placeholder_banner_1),
        DrawerState(DrawerValue.Closed)
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginFormLoginPreview() {
    LoginFormLogin(
        banner = painterResource(R.drawable.placeholder_banner_2),
        onBack = {},
        onGotoRegister = {}
    ){_,_,_->}
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginFormRegisterPreview() {
    LoginFormRegister(
        banner = painterResource(R.drawable.placeholder_banner_3),
        onBack = {},
        onGotoLogin = {}
    ) {}
}