package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenMainScreenLoads_thenShowCorrectComponents() {
        composeTestRule.setContent {
            MainScreen( {} )
        }

        composeTestRule.onNodeWithTag("mainHeader").assertIsDisplayed()

        composeTestRule.onNodeWithTag("addInviteButton").assertIsDisplayed()
    }

    @Test
    fun whenMainScreenLoads_thenNewInviteDialogIsHidden() {
        composeTestRule.setContent {
            MainScreen( {} )
        }

        composeTestRule.onNodeWithText("new invite", ignoreCase = true).assertIsNotDisplayed()
    }

    @Test
    fun whenNewInviteButtonClicked_thenNewInviteDialogShouldShow() {

        var dialogShowing = false

        composeTestRule.setContent {
            MainScreen( { dialogShowing = true } )
        }

        composeTestRule.onNodeWithTag("addInviteButton").performClick()

        assertTrue(dialogShowing)
    }
}