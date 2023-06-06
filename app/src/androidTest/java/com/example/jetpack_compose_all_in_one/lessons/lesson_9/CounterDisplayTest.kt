package com.example.jetpack_compose_all_in_one.lessons.lesson_9

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpack_compose_all_in_one.ui.views.main_ui.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterDisplayTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        composeRule.activity.setContent {
            CounterDisplay()
        }
    }

    @Test
    fun verifyIfAllViewsExists() {
        composeRule.apply {
            onNodeWithTag("Counter Display").assertExists()
            onNodeWithTag("Input").assertExists()
            onNodeWithText("Copy").assertExists()
        }
    }

    @Test
    fun whenInputIsEmptyThenCounterDisplayInvalidEntry() {
        composeRule.onNodeWithText("Copy").performClick()
        composeRule.onNodeWithTag("Counter Display").assertTextEquals("Invalid entry")
    }
}