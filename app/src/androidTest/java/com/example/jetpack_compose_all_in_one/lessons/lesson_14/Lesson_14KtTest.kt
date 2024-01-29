package com.example.jetpack_compose_all_in_one.lessons.lesson_14

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class Lesson14KtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInitialState(){

        composeTestRule.setContent {
            LessonContent()
        }

        composeTestRule.onNodeWithText("Key: \nValue: ").assertIsDisplayed()
    }

    @Test
    fun testLessonContent(){

        composeTestRule.setContent {
            LessonContent()
        }

        composeTestRule.onNodeWithText("Key2 - Canada").performClick()
        composeTestRule.onNodeWithText("Key2: Key2\nValue: Canada").assertIsDisplayed()
    }

    @Test
    fun testLessonSwitchCountry(){

        composeTestRule.setContent {
            LessonContent()
        }
        composeTestRule.onNodeWithText("Key2 - Canada").performClick()
        composeTestRule.onNodeWithText("Key2: Key2\nValue: Canada").assertIsDisplayed()

        composeTestRule.onNodeWithText("Key2 - Canada").performClick()
        composeTestRule.onNodeWithText("Key3: Key3\nValue: UK").assertIsDisplayed()



    }
}