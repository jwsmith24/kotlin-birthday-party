package com.example.kotlinbirthdayparty.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class InvitationCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val testCardData = Invitation(
        id = 0,
        name = "Jake Smith",
        hasPlusOne = true,
        rsvpStatus = RsvpStatus.Pending
    )

    @Test
    fun whenCardLoads_thenShowCorrectComponents() {
        composeTestRule.setContent {
            InvitationCard(
                testCardData,
                onToggle = {},
                onDelete = {}
            )
        }

        composeTestRule.onNodeWithTag("name")
            .assertIsDisplayed()
            .assertTextContains("Jake Smith")

        composeTestRule.onNodeWithTag("plusOne")
            .assertIsDisplayed()
            .assertTextContains("Plus one")

        composeTestRule.onNodeWithTag("rsvpStatus")
            .assertIsDisplayed()
            .assertTextContains("Status: Pending")
        // Delete button
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
}