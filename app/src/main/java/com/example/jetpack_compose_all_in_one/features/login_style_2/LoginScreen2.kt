package com.example.jetpack_compose_all_in_one.features.login_style_2

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.components.LabeledSwitch
import com.example.jetpack_compose_all_in_one.ui.theme.Blue10
import com.example.jetpack_compose_all_in_one.ui.theme.Pink10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_1
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_20
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50
import com.example.jetpack_compose_all_in_one.utils.showToast

@Composable
fun LoginScreen2() {
    val viewModel: LoginStyle2ViewModel = viewModel()
    val context = LocalContext.current
    var showPassword: Boolean by remember {
        mutableStateOf(false)
    }
    var isLoginScreen: Boolean by remember {
        mutableStateOf(true)
    }

    val loginStatus = viewModel.loginStatus.observeAsState()
    val registerStatus = viewModel.registerStatus.observeAsState()
    val verifyEmailStatus = viewModel.verifyEmailStatus.observeAsState()

    /* loginStatus.runCatching {
         if (loginStatus.value != "") {
             showToast(context, loginStatus.value!!)
         }
     }
     registerStatus.runCatching {
         if (registerStatus.value != "") {
             showToast(context, registerStatus.value!!)
         }
     }*/

    registerStatus.value?.let {
        showToast(context, it)
    }

    loginStatus.value?.let {
        showToast(context, it)
    }

    loginStatus.value?.let {
        showToast(context, it)
    }

    Box(
        modifier = Modifier
            .background(color = Blue10)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(dp_50)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.baseline_bloodtype_24),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = if (isLoginScreen) context.getString(R.string.login_label)
                else context.getString(R.string.register_label),
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            LabeledSwitch(
                Pair(
                    context.getString(R.string.login_label),
                    context.getString(R.string.register_label)
                ),
                Modifier
                    .padding(24.dp)
            ) { isLoginScreen = it }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                shape = RoundedCornerShape(24.dp),
                label = { Text(text = "Email", color = Color.White) },
                placeholder = { Text(text = "", color = Color.White) },
            )
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                shape = RoundedCornerShape(24.dp),
                label = { Text(text = "Password") },
                placeholder = { Text(text = "") },
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Outlined.VisibilityOff
                            else Icons.Outlined.Visibility,
                            contentDescription = ""
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
            )
            Text(
                text = "Forgot password?",
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(dp_20))

            OutlinedButton(
                border = BorderStroke(dp_1, Color.White),
                onClick = {
                    invokeViewModelMethods(
                        context = context,
                        isLoginScreen = isLoginScreen,
                        viewModel = viewModel
                    )
                }) {
                Text(
                    text = if (isLoginScreen) context.getString(R.string.login_label)
                    else context.getString(R.string.register_label)
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(dp_10)
                .align(Alignment.BottomStart),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Terms and condition")
        }
        Column(
            modifier = Modifier
                .padding(dp_10)
                .align(Alignment.BottomEnd),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FloatingActionButton(
                onClick = {

                },
                containerColor = Pink10,
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        }
    }
}

private fun invokeViewModelMethods(
    context: Context,
    isLoginScreen: Boolean,
    viewModel: LoginStyle2ViewModel
) = with(viewModel) {
    if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
        if (isLoginScreen) {
            login()
        } else {
            register()
        }
    } else {
        showToast(context, context.getString(R.string.empty_login))
    }
}
