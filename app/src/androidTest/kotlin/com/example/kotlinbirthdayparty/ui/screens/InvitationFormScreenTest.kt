package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class InvitationFormScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenFormScreenLoads_thenDisplayCorrectFields() {
        composeTestRule.setContent {
            InvitationFormScreen(
                name = "",
                hasPlusOne = false,
                onNameFieldChanged = { },
                onPlusOneChanged = { },
                nameHasErrors = MutableStateFlow(true),
                onConfirm = { true },
                onBackButtonClicked = {}
            )
        }

        composeTestRule.onNodeWithTag("formHeader").assertIsDisplayed()

        composeTestRule.onNodeWithTag("backButton").assertIsDisplayed()

        composeTestRule.onNodeWithTag("inputName").assertIsDisplayed()

        composeTestRule.onNodeWithTag("checkboxPlusOne").assertIsDisplayed()

        composeTestRule.onNodeWithTag("confirmButton").assertIsDisplayed()
    }

    @Test
    fun whenBackButtonClicked_thenCallCorrectCallback() {

        var backButtonClicked = false

        composeTestRule.setContent {
            InvitationFormScreen(
                name = "",
                hasPlusOne = false,
                onNameFieldChanged = { },
                onPlusOneChanged = { },
                nameHasErrors = MutableStateFlow(true),
                onConfirm = { true },
                onBackButtonClicked = { backButtonClicked = true }
            )
        }

        composeTestRule.onNodeWithTag("backButton").performClick()

        assertTrue(backButtonClicked)
    }

    @Test
    fun whenConfirmButtonClickedWithProperlyFilledInName_thenCallCorrectCallback() {

        var confirmClicked = false

        composeTestRule.setContent {
            InvitationFormScreen(
                name = "",
                hasPlusOne = false,
                onNameFieldChanged = { },
                onPlusOneChanged = { },
                nameHasErrors = MutableStateFlow(true),
                onConfirm = { confirmClicked = true
                            true },
                onBackButtonClicked = { }
            )
        }

        composeTestRule.onNodeWithTag("confirmButton").performClick()

        assertTrue(confirmClicked)
    }

    @Test
    fun whenConfirmButtonClickedWithoutFilledInName_thenDontNavBack() {

        var navBack = false

        composeTestRule.setContent {
            InvitationFormScreen(
                name = "",
                hasPlusOne = false,
                onNameFieldChanged = { },
                onPlusOneChanged = { },
                nameHasErrors = MutableStateFlow(true),
                onConfirm = { false },
                onBackButtonClicked = { navBack = true }
            )
        }

        composeTestRule.onNodeWithTag("confirmButton").performClick()

        assertFalse(navBack)
    }

    @Test
    fun whenFormIsFilled_thenFireCorrectCallbacks() {

        var typedText = false
        var checkedBox = false

        composeTestRule.setContent {
            InvitationFormScreen(
                name = "",
                hasPlusOne = false,
                onNameFieldChanged = { typedText = true },
                onPlusOneChanged = { checkedBox = true },
                nameHasErrors = MutableStateFlow(false),
                onConfirm = { false },
                onBackButtonClicked = { }
            )
        }

        composeTestRule.onNodeWithTag("inputName").performTextInput("hello")
        composeTestRule.onNodeWithTag("checkboxPlusOne").performClick()

        assertTrue(typedText)
        assertTrue(checkedBox)
    }
}