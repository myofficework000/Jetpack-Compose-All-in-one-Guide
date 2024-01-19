package com.example.jetpack_compose_all_in_one.lessons.lesson_18

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)

class ComposeUITestForInputFieldTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInputFields() {
        val email = "one@gmail.com"
        val password = "12345"

        composeTestRule.setContent {
            ComposeUITestForInputField { mail, pass ->
                assert(mail == email)
                assert(pass == pass)
            }
        }

        composeTestRule
            .onNodeWithText("Enter email")
            .performTextInput(email)

        composeTestRule
            .onNodeWithText("Enter password")
            .performTextInput(password)

        composeTestRule
            .onNodeWithText("Submit")
            .performClick()

        assert(email.isNotEmpty())
        assert(password.isNotEmpty())
    }
}