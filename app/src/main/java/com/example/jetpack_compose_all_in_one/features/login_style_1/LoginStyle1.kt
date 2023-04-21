package com.example.jetpack_compose_all_in_one.features.login_style_1

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.views.sign_in_sign_up.RegistrationForm
import com.example.jetpack_compose_all_in_one.ui.components.OutlinedButton
import com.example.jetpack_compose_all_in_one.ui.components.SimpleIconButton
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton

@Composable
fun LoginPage() {
    var displayMode by remember{ mutableStateOf("") }

    when (displayMode) {
        "login" -> {
            LoginFormLogin(
                banner = painterResource(R.drawable.placeholder_banner_2),
                onBack = { displayMode = "" }
            ) {}
        }
        "register" -> {
            LoginFormRegister(
                banner = painterResource(R.drawable.placeholder_banner_3),
                onBack = { displayMode = "" }
            ) {}
        }
        else -> {
            LoginMainMenu(
                banner = painterResource(R.drawable.placeholder_banner_1)
            ) {
                displayMode = it
            }
        }
    }
}

@Composable
fun LoginMainMenu(
    banner: Painter? = null,
    onPageChange: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            banner?.run{
                Image(this,"",
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    contentScale = ContentScale.FillWidth
                )
            }
        },
        containerColor = Color(0.5f,0.5f,1.0f)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it), contentAlignment = Alignment.Center) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Title", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                Text("Desc desc desc desc desc desc", color = Color.White, fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFormLogin(
    banner: Painter? = null,
    onBack: () -> Unit,
    onLogin: () -> Unit
) {
    BackHandler { onBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    banner?.run{
                        Image(this,"",
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    SimpleIconButton(
                        R.drawable.baseline_arrow_back_24
                    ) { onBack() }
                }
            )
        },
        containerColor = Color(0.5f,0.5f,1.0f)
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFormRegister(
    banner: Painter? = null,
    onBack: () -> Unit,
    onRegister: () -> Unit
) {
    BackHandler { onBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    banner?.run{
                        Image(this,"",
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    SimpleIconButton(
                        R.drawable.baseline_arrow_back_24
                    ) { onBack() }
                }
            )
        },
        containerColor = Color(0.5f,0.5f,1.0f)
    ) {
        RegistrationForm(
            Modifier.padding(it)
        ) {

        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginPagePreview() {
    LoginPage()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginMainMenuPreview() {
    LoginMainMenu(
        banner = painterResource(R.drawable.placeholder_banner_1)
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginFormLoginPreview() {
    LoginFormLogin(
        banner = painterResource(R.drawable.placeholder_banner_2),
        onBack = {}
    ){}
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginFormRegisterPreview() {
    LoginFormRegister(
        banner = painterResource(R.drawable.placeholder_banner_3),
        onBack = {}
    ) {}
}