package com.example.jetpack_compose_all_in_one

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.jetpack_compose_all_in_one.ui.theme.spaceSmall


@Composable
fun InputFields() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleTextField()
        Spacer(modifier = Modifier.padding(spaceSmall))
        LabelAndPlaceHolder()
        Spacer(modifier = Modifier.padding(spaceSmall))
        OutlineTextField()
        Spacer(modifier = Modifier.padding(spaceSmall))
        TextFieldWithIcons()
        Spacer(modifier = Modifier.padding(spaceSmall))
        PasswordTextField()
        Spacer(modifier = Modifier.padding(spaceSmall))
        TextFieldWithNumbers()
    }
}


@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text, onValueChange = { newText ->
        text = newText
    })
}

@Composable
fun LabelAndPlaceHolder() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text, onValueChange = { text = it },
        label = { Text(text = "Username") },
        placeholder = { Text(text = "username") }
    )
}

@Composable
fun OutlineTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = "Enter Your Name") }
    )
}

@Composable
fun TextFieldWithIcons() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = { text = it },
        label = { Text(text = "Email") }
    )
}

@Composable
fun TextFieldWithNumbers() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onValueChange = { text = it },
        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "phoneIcon") },
        label = { Text(text = "Mobile Number") }
    )
}

@Composable
private fun PasswordTextField() {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { text = it },
        label = { Text(text = "Password") },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}