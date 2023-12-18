package com.example.jetpack_compose_all_in_one.ui.views.lessons

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeLayoutsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun composeLayouts_shouldDisplayText() {
        composeTestRule.setContent {
            ComposeLayouts()
        }

        composeTestRule.onNodeWithText("This is a column")
            .assertExists()
        composeTestRule.onNodeWithText("This is a row")
            .assertExists()
        composeTestRule.onNodeWithText("This is a box that with no aligning")
            .assertExists()
        composeTestRule.onNodeWithText("This is a box that with aligning with Parent")
            .assertExists()
        composeTestRule.onNodeWithText("This is a box that with aligning with Parent Edges and center")
            .assertExists()
    }

    @Test
    fun composeLayouts_shouldDisplayColumnItems() {
        composeTestRule.setContent {
            ComposeLayouts()
        }

        composeTestRule.onNodeWithTag("column_one")
            .assertExists()
        composeTestRule.onNodeWithTag("column_two")
            .assertExists()
        composeTestRule.onNodeWithTag("column_three")
            .assertExists()
        composeTestRule.onNodeWithTag("column_four")
            .assertExists()
        composeTestRule.onNodeWithTag("column_five")
            .assertExists()
    }

    @Test
    fun composeLayouts_shouldDisplayRowItems() {
        composeTestRule.setContent {
            ComposeLayouts()
        }

        composeTestRule.onNodeWithTag("row_one")
            .assertExists()
        composeTestRule.onNodeWithTag("row_two")
            .assertExists()
        composeTestRule.onNodeWithTag("row_three")
            .assertExists()
        composeTestRule.onNodeWithTag("row_four")
            .assertExists()
        composeTestRule.onNodeWithTag("row_five")
            .assertExists()
    }

    @Test
    fun composeLayouts_shouldDisplayBoxItems() {
        composeTestRule.setContent {
            ComposeLayouts()
        }

        composeTestRule.onNodeWithText("Top Center")
            .assertExists()
        composeTestRule.onNodeWithText("Center Start")
            .assertExists()
        composeTestRule.onNodeWithText("Center End")
            .assertExists()
        composeTestRule.onNodeWithText("Bottom Center")
            .assertExists()
    }
}