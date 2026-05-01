package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
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

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val previewCards = MutableStateFlow(listOf(
        Invitation(
            name = "Jake Smith",
            hasPlusOne = true,
            rsvpStatus = RsvpStatus.Pending
        ),
        Invitation(
            name = "Curt Arbtin",
            hasPlusOne = true,
            rsvpStatus = RsvpStatus.Accepted
        ),
        Invitation(
            name = "Rob Payne",
            hasPlusOne = false,
            rsvpStatus = RsvpStatus.Declined
        )
    ))

    @Test
    fun whenMainScreenLoads_thenShowCorrectComponents() {
        composeTestRule.setContent {
            MainScreen(
                previewCards,
                onAddInvite = {},
                status = MutableStateFlow(intArrayOf(1, 2, 3)),
                {}) {}
        }

        composeTestRule.onNodeWithTag("mainHeader").assertIsDisplayed()

        composeTestRule.onNodeWithTag("statusCounter").assertIsDisplayed()

        composeTestRule.onNodeWithTag("cardContainer").assertIsDisplayed()

        composeTestRule.onNodeWithTag("addInviteButton").assertIsDisplayed()
    }

    @Test
    fun whenInvitesNull_thenShowNoInvitesIndicator() {
        composeTestRule.setContent {
            MainScreen(
                MutableStateFlow(emptyList()),
                onAddInvite = {},
                status = MutableStateFlow(intArrayOf(0, 0, 0)),
                {}) {}
        }

        composeTestRule.onNodeWithContentDescription("noCardsIcon").assertIsDisplayed()
    }

    @Test
    fun whenInvitesNotNull_thenShowCorrectNumberOfCards() {
        composeTestRule.setContent {
            MainScreen(
                previewCards,
                onAddInvite = {},
                status = MutableStateFlow(intArrayOf(1, 2, 3)),
                {}) {}
        }

        composeTestRule.onAllNodesWithContentDescription("card")
            .assertCountEquals(3)
    }

    @Test
    fun whenNewInviteButtonClicked_thenCorrectNavCallShouldFire() {

        var plusButtonClicked = false

        composeTestRule.setContent {
            MainScreen(
                previewCards,
                onAddInvite = { plusButtonClicked = true },
                status = MutableStateFlow(intArrayOf(1, 2, 3)),
                {}) {}
        }

        composeTestRule.onNodeWithTag("addInviteButton").performClick()

        assertTrue(plusButtonClicked)
    }
}