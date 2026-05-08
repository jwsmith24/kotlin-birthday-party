package com.example.kotlinbirthdayparty.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.kotlinbirthdayparty.TestHelper
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class StatusCounterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val testCounterData = MutableStateFlow(TestHelper.invitations)


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
            .assertTextContains("1", substring = true)

        composeTestRule.onNodeWithContentDescription("labelAccepted")
            .assertTextContains("1", substring = true)

        composeTestRule.onNodeWithContentDescription("labelDeclined")
            .assertTextContains("1", substring = true)

    }

}