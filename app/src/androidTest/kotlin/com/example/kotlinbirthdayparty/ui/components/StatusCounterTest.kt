package com.example.kotlinbirthdayparty.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class StatusCounterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val testCounterData = MutableStateFlow(
        listOf(
            Invitation(
                id = 0,
                name = "Jacob Varner",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                id = 1,
                name = "Curt Arbtin",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                id = 2,
                name = "Rob Payne",
                hasPlusOne = false,
                rsvpStatus = RsvpStatus.Declined
            )
        )
    )


    @Test
    fun whenCounterLoads_thenDisplayCorrectComponents() {
        composeTestRule.setContent {
            StatusCounter(testCounterData)
        }

        composeTestRule.onNodeWithContentDescription("labelPending")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("labelAccepted")
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("labelDeclined")
            .assertIsDisplayed()
    }

    @Test
    fun whenCounterLoads_thenDisplayCorrectCounts() {
        composeTestRule.setContent {
            StatusCounter(testCounterData)
        }

        composeTestRule.onNodeWithContentDescription("labelPending")
            .assertTextContains("2", substring = true)

        composeTestRule.onNodeWithContentDescription("labelAccepted")
            .assertTextContains("0", substring = true)

        composeTestRule.onNodeWithContentDescription("labelDeclined")
            .assertTextContains("1", substring = true)

    }

}