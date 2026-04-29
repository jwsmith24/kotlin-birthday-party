package com.example.kotlinbirthdayparty

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class GreetingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenGreetingCalledWithName_thenNameDisplayed() {
        composeTestRule.setContent {
            Greeting(name = "Fred")
        }

        composeTestRule.onNodeWithText("Hi Fred!")
            .assertIsDisplayed()

    }
}