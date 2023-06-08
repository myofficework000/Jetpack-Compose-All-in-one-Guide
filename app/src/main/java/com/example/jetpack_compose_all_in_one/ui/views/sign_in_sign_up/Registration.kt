package com.example.jetpack_compose_all_in_one.ui.views.sign_in_sign_up

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.features.sign_in_sign_up.ValidateRegistration.isValidRegistrationInput
import com.example.jetpack_compose_all_in_one.ui.components.SimpleTextButton
import com.example.jetpack_compose_all_in_one.ui.components.TextFieldWithNumbers
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
import com.example.jetpack_compose_all_in_one.ui.theme.spaceSmall
import com.example.jetpack_compose_all_in_one.utils.showLongToast
import com.example.jetpack_compose_all_in_one.utils.showToast

@Preview
@Composable
fun RegistrationPreview() {
    RegistrationForm {

    }
}

@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
    onRegister: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.testTag("Register"),
                text = "Register",
                fontSize = sp_32,
                color = Color.Blue,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            SpacerSmall()

            //username
            UserTextField()
            SpacerSmall()

            //email input field
            EmailTextField()
            SpacerSmall()

            //
            TextFieldWithNumbers()
            SpacerSmall()

            //password input field
            PasswordTextField()
            SpacerSmall()

            //confirm password input field
            ConfirmPasswordTextField()
            SpacerSmall()

            //register button
            SimpleTextButton("Register") {
                onRegister
            }
        }
    }


}

fun buttonClick(context: Context) {

    if (isValidRegistrationInput(
            username = username.value,
            password = password,
            confirmPassword = confirmPassword
        )
    ) {
        Log.d("TAG", username.value)
        Log.d("TAG", password)
        Log.d("TAG", confirmPassword)
        showToast(context = context, context.getString(R.string.registration_success))
    } else {
        showLongToast(context = context, context.getString(R.string.registration_fail))
    }
}

@Composable
fun SpacerSmall() {
    Spacer(modifier = Modifier.padding(spaceSmall))
}

@Composable
fun RegisterButton() {
    val context = LocalContext.current
    Button(
        onClick = { buttonClick(context = context) },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = "Register", fontSize = sp_16)
    }
}

@Composable
fun UserTextField() {
    OutlinedTextField(
        value = username.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "userIcon") },
        onValueChange = { username.value = it },
        label = { Text(text = "Username") }
    )
}


@Composable
fun EmailTextField() {

    OutlinedTextField(
        value = email.value,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = {
            email.value = it
            validateEmail(email = it)
        },
        isError = isEmailValid.value,
        label = { Text(text = "Email") }
    )
}

@Composable
fun ConfirmPasswordTextField() {
    OutlinedTextField(
        value = confirmPassword,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { confirmPassword = it },
        label = { Text(text = "Confirm Password") },
    )
}

@Composable
private fun PasswordTextField() {

    val showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = password,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { password = it },
        label = { Text(text = "Password") },
    )
}

fun validateEmail(email: String) {
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        isEmailValid.value = true
        emailErrMsg.value = "Input proper email id"
    } else {
        isEmailValid.value = false
        emailErrMsg.value = ""
    }
}

var regUser: RegisterUser = RegisterUser()
var username: MutableState<String> = mutableStateOf(regUser.name)
var password: String by mutableStateOf("")
var confirmPassword: String by mutableStateOf("")
var email: MutableState<String> = mutableStateOf(regUser.email)
var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
var emailErrMsg: MutableState<String> = mutableStateOf("")

data class RegisterUser(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)

