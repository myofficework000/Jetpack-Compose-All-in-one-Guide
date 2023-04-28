package com.example.jetpack_compose_all_in_one.features.login_style_2

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.Blue10
import com.example.jetpack_compose_all_in_one.ui.theme.Pink10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_50

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreen2() {
    var email: String by remember {
        mutableStateOf("")
    }
    var password: String by remember {
        mutableStateOf("")
    }
    var showPassword: Boolean by remember {
        mutableStateOf(false)
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
                text = "Login",
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(24.dp),
                label = { Text(text = "Email", color = Color.White) },
                placeholder = { Text(text = "", color = Color.White) },
            )
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
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
            Spacer(modifier = Modifier.padding(20.dp))

            OutlinedButton(
                border = BorderStroke(1.dp, Color.White),
                onClick = { }) {
                Text(text = "Login")
            }
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomStart),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Terms and condition")
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
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
