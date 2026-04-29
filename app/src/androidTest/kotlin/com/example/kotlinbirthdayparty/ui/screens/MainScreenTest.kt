package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import org.junit.Rule

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    fun whenMainScreenLoads_thenShowCorrectComponents() {
        composeTestRule.setContent {
            MainScreen()
        }

        composeTestRule.onNodeWithTag("mainScreenScaffold").assertIsDisplayed()

        composeTestRule.onNodeWithTag("mainHeader").assertIsDisplayed()

        composeTestRule.onNodeWithTag("addInviteButton").assertIsDisplayed()
    }

    fun whenMainScreenLoads_thenNewInviteDialogIsHidden() {
        composeTestRule.setContent {
            MainScreen()
        }

        composeTestRule.onNodeWithText("new invite", ignoreCase = true).assertIsNotDisplayed()
    }

    fun whenNewInviteButtonClicked_thenNewInviteDialogShouldShow() {

        val dialogShowing = false

        composeTestRule.setContent {
            MainScreen()
        }

        composeTestRule.onNodeWithTag("addInviteButton").performClick()

        assertTrue(dialogShowing)
    }
}