package com.example.jetpack_compose_all_in_one.ui.views.sign_in_sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.ui.components.CheckboxText
import com.example.jetpack_compose_all_in_one.ui.components.GradientButton
import com.example.jetpack_compose_all_in_one.ui.components.GradientTextField
import com.example.jetpack_compose_all_in_one.ui.components.TextButton
import com.example.jetpack_compose_all_in_one.ui.theme.Blue10ToBlue20
import com.example.jetpack_compose_all_in_one.ui.theme.WhiteToBlue20

@Composable
fun LoginForm1(
    modifier: Modifier = Modifier,
    onGotoRegister: () -> Unit = {},
    onLogin: (String, String, Boolean) -> Unit
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val rememberMe = rememberSaveable { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome back",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Log in to continue",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(Modifier.size(16.dp))

            EmailField(email)
            SpacerSmall()
            PasswordField(password)
            ExtrasField(rememberMe){}

            Spacer(Modifier.weight(1f))

            SubmitBox(onGotoRegister = onGotoRegister) {
                onLogin(email.value, password.value, rememberMe.value)
            }
        }
    }
}

@Composable
private fun EmailField(
    state: MutableState<String>
) {
    GradientTextField(
        state.value,
        { state.value = it },
        WhiteToBlue20,
        Modifier.fillMaxWidth(),
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
        label = { Text(text = "Email") }
    )
}

@Composable
private fun PasswordField(
    state: MutableState<String>
) {
    GradientTextField(
        state.value,
        { state.value = it },
        WhiteToBlue20,
        Modifier.fillMaxWidth(),
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
        label = { Text(text = "Password") }
    )
}

@Composable
private fun ExtrasField(
    state: MutableState<Boolean>,
    onForgetPassword: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CheckboxText("Remember me", state.value) { state.value = !state.value }
        TextButton("Forget Password?") { onForgetPassword() }
    }
}

@Composable
private fun SubmitBox(
    onGotoRegister: () -> Unit,
    onSubmit: () -> Unit
) {
    Column {
        GradientButton(
            "Login",
            gradient = Blue10ToBlue20,
            modifier = Modifier.fillMaxWidth(),
            textColor = Color.White
        ) { onSubmit() }

        Spacer(Modifier.size(4.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("New to this app? ")
            TextButton("Sign up!", hasPadding = false) { onGotoRegister() }
        }
    }
}