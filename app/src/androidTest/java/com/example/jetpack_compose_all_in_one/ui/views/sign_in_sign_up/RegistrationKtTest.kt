package com.example.jetpack_compose_all_in_one.ui.views.sign_in_sign_up

import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpack_compose_all_in_one.lessons.lesson_9.CounterDisplay
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
        }
    }

    /*fun verifyEmailValidation(){
        composeTestRule.apply {
            onNodeWithText("Email")
        }
    }*/
}