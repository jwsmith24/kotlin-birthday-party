package com.example.kotlinbirthdayparty.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.example.kotlinbirthdayparty.invitation.Invitation
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class InvitationCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val testCardData = Invitation(
        name = "Jake Smith",
        address = "123 Main st.",
        hasPlusOne = true,
    )

    @Test
    fun whenCardLoads_thenCorrectComponentsShow() {
        composeTestRule.setContent {
            InvitationCard(
                testCardData,
                onToggle = {},
                onDelete = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("name")
            .assertIsDisplayed()
            .assertTextContains("Jake Smith +1")

        composeTestRule.onNodeWithContentDescription("address")
            .assertIsDisplayed()
            .assertTextContains("123 Main st.")

        composeTestRule.onNodeWithContentDescription("rsvpStatus")
            .assertIsDisplayed()
            .assertTextContains("Status: Pending")

        composeTestRule.onNodeWithContentDescription("deleteButton")
            .assertIsDisplayed()
    }

    @Test
    fun whenUserClicksOnCard_thenRSVPStatusChanges() {
        var cardClicked = false

        composeTestRule.setContent {
            InvitationCard(
                testCardData,
                onToggle = { cardClicked = true },
                onDelete = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("card").performClick()

        assertTrue(cardClicked)
    }

    @Test
    fun whenUserClicksDelete_thenCorrectCallbackFires() {
        var deleteClicked = false

        composeTestRule.setContent {
            InvitationCard(
                testCardData,
                onToggle = {},
                onDelete = { deleteClicked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("deleteButton").performClick()

        assertTrue(deleteClicked)
    }
}