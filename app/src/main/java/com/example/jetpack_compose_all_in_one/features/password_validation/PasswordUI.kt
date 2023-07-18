package com.example.jetpack_compose_all_in_one.features.password_validation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.jetpack_compose_all_in_one.ui.theme.dp_10
import com.example.jetpack_compose_all_in_one.ui.theme.dp_5
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8


@Composable
fun PasswordUi(
    viewModel: PasswordViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val passwordError by viewModel.passwordError.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = viewModel::changePassword,
            visualTransformation = PasswordVisualTransformation(),
            isError = !passwordError.successful
        )

        Spacer(modifier = Modifier.height(dp_8))

        Column(verticalArrangement = Arrangement.spacedBy(dp_5)) {
            ConditionRow(condition = "Minimum 6 characters", check = passwordError.hasMinimum)
            ConditionRow(condition = "Has special character", check = passwordError.hasSpecialCharacter)
            ConditionRow(condition = "Has capitalized letter", check = passwordError.hasCapitalizedLetter)
        }
    }
}

@Composable
fun ConditionRow(
    condition: String,
    check: Boolean
) {
    val color by animateColorAsState(
        targetValue = if (check) Color.Green else Color.Red,
        label = "text color"
    )

    val icon = if (check) {
        Icons.Rounded.Check
    } else {
        Icons.Rounded.Close
    }

    Row {
        Icon(
            imageVector = icon,
            tint = color,
            contentDescription = "status icon"
        )
        Spacer(modifier = Modifier.width(dp_10))
        Text(
            text = condition,
            color = color
        )
    }
}