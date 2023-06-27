package com.example.jetpack_compose_all_in_one.features.shared_pref.demo1

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10

@Composable
fun Demo1(context: Context){
    // Luan
    SPLoginScreen(context)
}

@Composable
fun SPLoginScreen(context: Context) {
    val viewModel = Demo1ViewModel()
    var email by remember{ mutableStateOf(TextFieldValue()) }
    var password  by remember{ mutableStateOf(TextFieldValue()) }
    var passwordVisible by remember{ mutableStateOf(false)}

    val savePrefSecurely by lazy {
        viewModel.savePrefSecurely(
            context,
            email.text,
            password.text
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(dp_10),
            verticalArrangement = Arrangement.spacedBy(dp_10),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {newEmail -> email = newEmail},
                label = { Text(stringResource(id = R.string.emailText)) },
                placeholder = { Text(stringResource(id = R.string.email_field))},
                trailingIcon = {
                    val image = Icons.Filled.Email
                    Icon(imageVector = image, contentDescription = "Email")
                }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {newPassword -> password = newPassword},
                label = { Text(stringResource(id = R.string.passwordText)) },
                placeholder = { Text(stringResource(id = R.string.password_field))},
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            Button(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
                onClick = { savePrefSecurely }
            ){
                Text(text = stringResource(id = R.string.secure_save_pref))
            }

        }
    }
}


@Preview
@Composable
private fun Demo1_Preview(){
    val context = LocalContext.current
    Demo1(context)
}

