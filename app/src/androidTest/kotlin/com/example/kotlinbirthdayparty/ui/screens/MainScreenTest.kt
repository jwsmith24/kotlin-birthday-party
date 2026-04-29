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
import junit.framework.TestCase.assertEquals
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
            MainScreen(previewCards) {}
        }

        composeTestRule.onNodeWithTag("mainHeader").assertIsDisplayed()

        composeTestRule.onNodeWithTag("cardContainer").assertIsDisplayed()

        composeTestRule.onNodeWithTag("addInviteButton").assertIsDisplayed()
    }

    @Test
    fun whenMainScreenLoads_thenNewInviteDialogIsHidden() {
        composeTestRule.setContent {
            MainScreen(previewCards) {}
        }

        composeTestRule.onNodeWithText("new invite", ignoreCase = true).assertIsNotDisplayed()
    }

    @Test
    fun whenInvitesNull_thenShowNoInvitesIndicator() {
        composeTestRule.setContent {
            MainScreen(MutableStateFlow(emptyList())) {}
        }

        composeTestRule.onNodeWithContentDescription("noCardsIcon").assertIsDisplayed()
    }

    @Test
    fun whenInvitesNotNull_thenShowCorrectNumberOfCards() {
        composeTestRule.setContent {
            MainScreen(previewCards) {}
        }

        composeTestRule.onAllNodesWithContentDescription("card")
            .assertCountEquals(3)
    }

    @Test
    fun whenNewInviteButtonClicked_thenNewInviteDialogShouldShow() {

        var plusButtonClicked = false

        composeTestRule.setContent {
            MainScreen(previewCards) { plusButtonClicked = true }
        }

        composeTestRule.onNodeWithTag("addInviteButton").performClick()

        assertTrue(plusButtonClicked)
    }
}