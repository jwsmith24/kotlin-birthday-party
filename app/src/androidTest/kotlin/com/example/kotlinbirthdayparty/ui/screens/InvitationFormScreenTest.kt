package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class InvitationFormScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenDialogOpens_thenDisplayCorrectFields() {
        composeTestRule.setContent {

        }

        composeTestRule.onNodeWithTag("dialogHeader").assertIsDisplayed()

        composeTestRule.onNodeWithTag("inputName").assertIsDisplayed()

        composeTestRule.onNodeWithTag("checkboxPlusOne").assertIsDisplayed()

        composeTestRule.onNodeWithTag("confirm").assertIsDisplayed()

        composeTestRule.onNodeWithTag("dismiss").assertIsDisplayed()
    }
}