package com.example.jetpack_compose_all_in_one

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jetpack_compose_all_in_one.lessons.lesson_9.CounterDisplay
import com.example.jetpack_compose_all_in_one.ui.theme.JetpackComposeAllInOneTheme
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)  // To indicate that we've to run it with AndroidJUnit runner
class CounterDisplayKtTest {

    @get: Rule
    val composeTestRule =
        createComposeRule()   // compose rule is required to get access to the composable component

    @Before
    fun setUp() {
        composeTestRule.setContent {    // setting our composable as content for test
            JetpackComposeAllInOneTheme {
                CounterDisplay(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black))
            }
        }
    }
}