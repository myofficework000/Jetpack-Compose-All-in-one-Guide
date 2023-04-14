package com.example.jetpack_compose_all_in_one.utils

import org.junit.Assert.*
import org.junit.Test

class ValidateRegistrationTest {

    @Test
    fun `When username is empty Then return false`() {
        val output = ValidateRegistration.isValidRegistrationInput(
            EMPTY_STRING,
            TEST_PASSWORD,
            TEST_CONFIRMED_PASSWORD
        )
        assertFalse(output)
    }

    @Test
    fun `When username,password,confirmedPassword is correct Then return true`() {
        val output = ValidateRegistration.isValidRegistrationInput(
            TEST_USERNAME,
            TEST_PASSWORD,
            TEST_CONFIRMED_PASSWORD
        )
        assertTrue(output)
    }

    @Test
    fun `When username already exist Then return false`() {
        val output = ValidateRegistration.isValidRegistrationInput(
            TEST_EXISTING_USERNAME,
            TEST_PASSWORD,
            TEST_CONFIRMED_PASSWORD
        )
        assertFalse(output)
    }

    @Test
    fun `When confirmPassword does not match Then return false`(){
        val output = ValidateRegistration.isValidRegistrationInput(
            TEST_USERNAME,
            TEST_PASSWORD,
            TEST_WRONG_CONFIRMED_PASSWORD
        )
        assertFalse(output)
    }

    @Test
    fun `When password is less then 6 char Then return false`() {
        val output = ValidateRegistration.isValidRegistrationInput(
            TEST_USERNAME,
            TEST_INVALID_PASSWORD,
            TEST_INVALID_PASSWORD
        )
        assertFalse(output)
    }

    private companion object {
        const val TEST_USERNAME = "test"
        const val TEST_PASSWORD = "12345678"
        const val TEST_CONFIRMED_PASSWORD = "12345678"
        const val TEST_INVALID_PASSWORD = "1234"
        const val TEST_WRONG_CONFIRMED_PASSWORD = "123456789"
        const val EMPTY_STRING = ""
        const val TEST_EXISTING_USERNAME = "Ryan"

    }
}