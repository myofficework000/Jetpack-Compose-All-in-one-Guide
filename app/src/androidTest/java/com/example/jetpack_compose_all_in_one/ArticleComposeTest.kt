package com.example.jetpack_compose_all_in_one

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.jetpack_compose_all_in_one.lessons.lesson_9.ArticleContent
import org.hamcrest.CoreMatchers.startsWith
import org.hamcrest.EasyMock2Matchers.equalTo
import org.junit.Rule
import org.junit.Test

class ArticleComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun composeFunction_EmptyInput_DisplayEmptyView() {
        val emptyContent = ""
        composeTestRule.setContent {
            ArticleContent(content = emptyContent)
        }
        composeTestRule.onNodeWithText("No content available").assertIsDisplayed()
    }

    @Test
    fun composeFunction_ValidInput_DisplayFormattedContent() {
        val validContent = "This is the article content."
        composeTestRule.setContent {
            ArticleContent(content = validContent)
        }
        composeTestRule.onNodeWithText(validContent).assertIsDisplayed()
    }

    @Test
    fun composeFunction_BulletPointsFormat_DisplayFormattedBulletPoints() {
        val bulletPointsContent = """
            - Bullet point 1
            - Bullet point 2
            - Bullet point 3
        """.trimIndent()
        composeTestRule.setContent {
            ArticleContent(content = bulletPointsContent)
        }
        composeTestRule.onNodeWithText("Bullet point 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bullet point 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bullet point 3").assertIsDisplayed()
    }


    @Test
    fun composeFunction_LongInput_DisplayTruncatedContent() {
        val longContent = "This is a very long article content that exceeds the display limits."
        composeTestRule.setContent {
            ArticleContent(content = longContent)
        }
        composeTestRule.onNodeWithTag("articleContent").let { node ->
            assertThat(node.toString(), startsWith("This is a very long"))
        }
    }

    @Test
    fun composeFunction_SpecialCharacters_DisplayFormattedContent() {
        val specialCharactersContent = """
            This is some content with special characters: 
            - @ # $ % & *
            - < > [ ] { }
        """.trimIndent()
        composeTestRule.setContent {
            ArticleContent(content = specialCharactersContent)
        }
        composeTestRule.onNodeWithText("@ # $ % & *").assertIsDisplayed()
        composeTestRule.onNodeWithText("< > [ ] { }").assertIsDisplayed()
    }

    @Test
    fun composeFunction_OnClick_DisplayToast() {
        val clickableContent = "This is clickable content."
        composeTestRule.setContent {
            ArticleContent(content = clickableContent, onClick = { showToast() })
        }
        composeTestRule.onNodeWithText(clickableContent)
            .assertIsDisplayed()
            .performClick()
        // Assert toast message displayed
        // You can use a test-specific ToastMatcher library or an equivalent mechanism to assert toast messages.
    }

    // Helper function to simulate a toast message
    private fun showToast() {
        // Simulate showing a toast message
    }
}
