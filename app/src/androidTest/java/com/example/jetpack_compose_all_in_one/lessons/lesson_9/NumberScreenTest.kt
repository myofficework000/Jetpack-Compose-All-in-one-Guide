package com.example.jetpack_compose_all_in_one.lessons.lesson_9

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NumberScreenTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NumberScreen()
        }
    }

    @Test
    fun lazyColumnScrollTest() {
        composeTestRule
            .onNodeWithTag("lazyColumn")
            .performScrollToIndex(6)

        composeTestRule
            .onNodeWithTag("lazyColumn")
            .onChildren()
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun lazyRowScrollTest() {
        composeTestRule
            .onNodeWithTag("lazyRow")
            .performScrollToIndex(2)

        composeTestRule
            .onNodeWithTag("lazyRow")
            .onChildren()[5]
            .assertIsDisplayed()
            .assertTextEquals("Item 6")
    }

    @Test
    fun testItemColorChangeOnClick() {
        val indexToTest = 4
        composeTestRule.onNodeWithTag("item$indexToTest").performClick()
        composeTestRule.onNodeWithTag("item$indexToTest").assertTextEquals("Clicked: Item $indexToTest")
    }
}