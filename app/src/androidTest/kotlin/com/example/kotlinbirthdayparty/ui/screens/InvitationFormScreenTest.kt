package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
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

@OptIn(ExperimentalMaterial3Api::class)
class InvitationFormScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    var state = InvitationFormState(
        name = "",
        address = "",
        hasPlusOne = false,
        onNameFieldChanged = { },
        onAddressFieldChanged = { },
        onPlusOneChanged = { },
        nameHasErrors = MutableStateFlow(true),
        addressHasErrors = MutableStateFlow(true),
        timePickerState = TimePickerState(22, 15, true),
        onConfirm = { false },
        onBackButtonClicked = {}
    )

    @Test
    fun whenFormScreenLoads_thenDisplayCorrectFields() {
        composeTestRule.setContent {
            InvitationFormScreen(state)
        }

        composeTestRule.onNodeWithTag("formHeader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("backButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("inputName").assertIsDisplayed()
        composeTestRule.onNodeWithTag("inputAddress").assertIsDisplayed()
        composeTestRule.onNodeWithTag("checkboxPlusOne").assertIsDisplayed()
        composeTestRule.onNodeWithTag("timePicker").assertIsDisplayed()
        composeTestRule.onNodeWithTag("confirmButton").assertIsDisplayed()
    }

    @Test
    fun whenBackButtonClicked_thenCallCorrectCallback() {

        var backButtonClicked = false

        state = state.copy(onBackButtonClicked = { backButtonClicked = true })

        composeTestRule.setContent { InvitationFormScreen(state) }

        composeTestRule.onNodeWithTag("backButton").performClick()

        assertTrue(backButtonClicked)
    }

    @Test
    fun whenConfirmButtonClicked_thenCallCorrectCallback() {

        var confirmClicked = false

        state = state.copy(onConfirm = { confirmClicked = true; true })

        composeTestRule.setContent { InvitationFormScreen(state) }

        composeTestRule.onNodeWithTag("confirmButton").performClick()

        assertTrue(confirmClicked)
    }


    @Test
    fun whenConfirmButtonClickedWithoutFilledInName_thenDontNavBack() {

        var navBack = false

        state = state.copy(onBackButtonClicked = { navBack = true })

        composeTestRule.setContent { InvitationFormScreen(state) }

        composeTestRule.onNodeWithTag("confirmButton").performClick()

        assertFalse(navBack)
    }

    @Test
    fun whenConfirmButtonClickedWithoutFilledInAddress_thenDontNavBack() {

        var navBack = false

        state = state.copy(onBackButtonClicked = { navBack = true })

        composeTestRule.setContent { InvitationFormScreen(state) }

        composeTestRule.onNodeWithTag("confirmButton").performClick()

        assertFalse(navBack)
    }

    @Test
    fun whenFormIsFilled_thenFireCorrectCallbacks() {

        var typedName = false
        var typedAddress = false
        var checkedBox = false

        state = state.copy(
            onNameFieldChanged = { typedName = true },
            onAddressFieldChanged = { typedAddress = true },
            onPlusOneChanged = { checkedBox = true}
        )

        composeTestRule.setContent { InvitationFormScreen(state) }

        composeTestRule.onNodeWithTag("inputName").performTextInput("hello")
        composeTestRule.onNodeWithTag("inputAddress").performTextInput("world")
        composeTestRule.onNodeWithTag("checkboxPlusOne").performClick()

        assertTrue(typedName)
        assertTrue(typedAddress)
        assertTrue(checkedBox)
    }
}