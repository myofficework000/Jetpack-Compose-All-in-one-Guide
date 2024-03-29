package com.example.jetpack_compose_all_in_one

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpack_compose_all_in_one.demos.thread.ThreadCommentSection
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ThreadCommentSectionTest {
    private lateinit var icon: SemanticsNodeInteraction
    private lateinit var firstOneMessageText: SemanticsNodeInteraction
    private lateinit var sendMessageHereText: SemanticsNodeInteraction

    @get:Rule
    val composableRule = createComposeRule()

    @Before
    fun setUp(){
        composableRule.setContent {
            ThreadCommentSection()
        }
        icon = composableRule.onNodeWithTag("comment_icon")
        firstOneMessageText = composableRule.onNodeWithTag("first_comment_static_text")
        sendMessageHereText = composableRule.onNodeWithTag("start_conversation_static_text")
    }

    @Test
    fun uiComponentTest() {
        icon.assertExists()
        firstOneMessageText.assertExists()
        sendMessageHereText.assertExists()
    }

    @Test
    fun startConversationTextClickableTest(){
        sendMessageHereText.performClick()
        sendMessageHereText.assertDoesNotExist()
    }
}