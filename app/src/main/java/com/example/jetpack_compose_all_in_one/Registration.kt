package com.example.jetpack_compose_all_in_one

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.theme.sp_16
import com.example.jetpack_compose_all_in_one.ui.theme.sp_32
import com.example.jetpack_compose_all_in_one.ui.theme.spaceSmall
import com.example.jetpack_compose_all_in_one.utils.ValidateRegistration.isValidRegistrationInput
import com.example.jetpack_compose_all_in_one.utils.showToast
import org.w3c.dom.Text

@Composable
fun RegistrationForm() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val confirmPassword = remember { mutableStateOf("") }

        Text(
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
        RegisterButton()

    }

}

fun buttonClick(context:Context){

    if (isValidRegistrationInput(
        username = username,
        password = password,
        confirmPassword = confirmPassword
        )
    ) {
        Log.d("TAG",username)
        Log.d("TAG",password)
        Log.d("TAG",confirmPassword)
        showToast(context = context, context.getString(R.string.registration_success))
    }
        else{
            showToast(context = context, context.getString(R.string.registration_fail) )

    }
}

@Composable
fun SpacerSmall(){
    Spacer(modifier = Modifier.padding(spaceSmall))
}

@Composable
fun RegisterButton() {
    val context = LocalContext.current
    Button(
        onClick = { buttonClick(context =context) },
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = "Register", fontSize = sp_16)
    }
}

@Composable
fun UserTextField() {
    OutlinedTextField(
        value = username,
        leadingIcon = {Icon(imageVector = Icons.Default.Person, contentDescription = "userIcon")},
        onValueChange = { username = it },
        label = { Text(text = "Username") }
    )
}


@Composable
fun EmailTextField() {

    OutlinedTextField(
        value = email,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = { email = it },
        label = { Text(text = "Email") }
    )
}

@Composable
fun ConfirmPasswordTextField(){

    val showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = confirmPassword,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { confirmPassword = it },
        label = { Text(text = "Confirm Password") },
        //visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
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
        //visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}


    var email : String by mutableStateOf("")
    var username : String by mutableStateOf("")
    var password : String by mutableStateOf("")
    var confirmPassword: String by mutableStateOf("")