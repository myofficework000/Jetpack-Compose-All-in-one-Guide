package com.example.jetpack_compose_all_in_one

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpack_compose_all_in_one.ui.views.input.PersonInput
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PersonInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPersonInput(){
        val hobby = "hiking"
        val favoriteSport = "swimming"

        composeTestRule.setContent {
            PersonInput()
        }

        composeTestRule.onNodeWithTag("hobbyTf")
            .performTextInput(hobby)

        composeTestRule.onNodeWithTag("sportTf")
            .performTextInput(favoriteSport)

        Assert.assertTrue(hobby.isNotEmpty())
        Assert.assertTrue(favoriteSport.isNotEmpty())
    }
}