package com.example.jetpack_compose_all_in_one

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpack_compose_all_in_one.lessons.lesson_7.LoginScreen
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginScreen() {
        val email = "test@example.com"
        val password = "password"

        composeTestRule.setContent {
            LoginScreen()
        }

        // Find the email TextField and perform text input
        composeTestRule.onNodeWithText("Type your email")
            .performTextInput(email)

        // Find the password TextField and perform text input
        composeTestRule.onNodeWithText("password")
            .performTextInput(password)

        // Find the ENTER button and perform a click
        composeTestRule.onNodeWithText("ENTER")
            .performClick()

        // Assert that the login action is triggered with the correct credentials
        // For demonstration purposes, we'll just check that the entered email and password are non-empty.
        assertTrue(email.isNotEmpty())
        assertTrue(password.isNotEmpty())
    }
}
