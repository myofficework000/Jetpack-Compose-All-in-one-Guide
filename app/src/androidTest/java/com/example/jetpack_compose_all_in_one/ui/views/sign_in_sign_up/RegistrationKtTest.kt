package com.example.jetpack_compose_all_in_one.ui.views.sign_in_sign_up

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            RegistrationForm{

            }
        }
    }

    @Test
    fun verifyAllViewExits(){
        composeTestRule.apply {
            onNodeWithTag("Register").assertExists()
            onNodeWithText("Username").assertExists()
            onNodeWithText("Email").assertExists()
            onNodeWithText("Mobile Number").assertExists()
            onNodeWithText("Password").assertExists()
            onNodeWithText("Confirm Password").assertExists()
            onNodeWithTag("Register button").assertExists()
        }
    }

    @Test
    fun verifyEmailValidationWithValidEmail(){
        val validEmail = "abcd@gmail.com"

        composeTestRule.onNodeWithText("Email").apply {
            performTextInput(validEmail)
            assert(hasText(validEmail))
            assertTrue(!isEmailValid.value)
            assertTrue(emailErrMsg.value == "")
            performTextClearance()
        }
    }

    @Test
    fun verifyEmailValidationWithInvalidEmail(){
        val invalidEmail = "invalidEmail"
        val errorMsg = "Input proper email id"

        composeTestRule.onNodeWithText("Email").apply {
            performTextInput(invalidEmail)
            assert(hasText(invalidEmail))
            assertTrue(isEmailValid.value)
            assertTrue(errorMsg == emailErrMsg.value)
            performTextClearance()
        }
    }

    @Test
    fun verifyConfirmPasswordValidationWithValidPassword(){
        val validConfirmPassword = ""
        val errorMsg = ""

        composeTestRule.onNodeWithText("Confirm Password").apply {
            performTextInput(validConfirmPassword)
            assert(hasText(validConfirmPassword))
            assertFalse(isConfirmPasswordInvalid.value)
            assertTrue(errorMsg == "")
            performTextClearance()
        }
    }

    @Test
    fun verifyConfirmPasswordValidationWithInvalidPassword(){
        val validConfirmPassword = "password"
        val errorMsg = "Password is not matched"

        composeTestRule.onNodeWithText("Confirm Password").apply {
            performTextInput(validConfirmPassword)
            assert(hasText(validConfirmPassword))
            assertTrue(isConfirmPasswordInvalid.value)
            assertTrue(errorMsg == "Password is not matched")
            performTextClearance()
        }
    }

    @Test
    fun verifyButtonClick(){
        val userName = "user"
        val email = "abc@abc.com"
        val mobile = "123-456-789"
        val password = "password"
        val confirmPassword = "password"

        composeTestRule.onNodeWithText("Username")
            .performTextInput(userName)

        composeTestRule.onNodeWithText("Email")
            .performTextInput(email)

        composeTestRule.onNodeWithText("Mobile Number")
            .performTextInput(mobile)

        composeTestRule.onNodeWithText("Password")
            .performTextInput(password)

        composeTestRule.onNodeWithText("Confirm Password")
            .performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag("Register button").performClick()

        assertTrue(email.isNotEmpty())
        assertTrue(password.isNotEmpty())
    }
}