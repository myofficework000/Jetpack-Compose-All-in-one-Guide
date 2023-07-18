package com.example.jetpack_compose_all_in_one.features.password_validation

import java.util.regex.Pattern

class PasswordValidation {

    fun execute(password: String): PasswordValidationState {
        val validateSpecialCharacter = validateSpecialCharacter(password)
        val validateCapitalizedLetter = validateCapitalizedLetter(password)
        val validateMinimum = validateMinimum(password)

        val hasError = listOf(
            validateMinimum,
            validateCapitalizedLetter,
            validateSpecialCharacter
        ).all { it }

        return PasswordValidationState(
            hasMinimum = validateMinimum,
            hasSpecialCharacter = validateSpecialCharacter,
            hasCapitalizedLetter = validateCapitalizedLetter,
            successful = hasError
        )
    }

    private fun validateSpecialCharacter(password: String): Boolean =
        password.contains(Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]").toRegex())

    private fun validateCapitalizedLetter(password: String): Boolean =
        password.contains(Regex(".*[A-Z].*"))

    private fun validateMinimum(password: String): Boolean =
        password.matches(Regex(".{6,}"))
}