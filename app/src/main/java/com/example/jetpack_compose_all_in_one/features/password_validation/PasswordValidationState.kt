package com.example.jetpack_compose_all_in_one.features.password_validation

data class PasswordValidationState(

    val hasMinimum: Boolean = false,
    val hasCapitalizedLetter: Boolean = false,
    val hasSpecialCharacter: Boolean = false,
    val successful: Boolean = false
)
