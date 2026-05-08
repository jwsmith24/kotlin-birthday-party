package com.example.kotlinbirthdayparty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinbirthdayparty.ui.screens.InvitationFormScreen
import com.example.kotlinbirthdayparty.ui.screens.InvitationFormState
import com.example.kotlinbirthdayparty.ui.screens.InvitationFormViewModel
import com.example.kotlinbirthdayparty.ui.screens.MainScreen
import com.example.kotlinbirthdayparty.ui.screens.MainScreenViewModel

@Composable
fun NavGraph(
    mainViewModel: MainScreenViewModel,
    invitationViewModel: InvitationFormViewModel
) {

    val navController = rememberNavController()

    // Will need when ATAK prevents using normal android viewModel lifecycle
    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.route) {
            Screen.Main.route -> {
                mainViewModel.onLoad()
            }

            Screen.InvitationForm.route -> {
                mainViewModel.onUnload()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                cards = mainViewModel.invites,
                onToggle = mainViewModel::onToggle,
                onDelete = mainViewModel::onDelete,
                onAddInvite = { navController.navigate(Screen.InvitationForm.route) }
            )
        }

        composable(Screen.InvitationForm.route) {
            val invitation by invitationViewModel.invitation.collectAsState()

            val formState = InvitationFormState(
                name = invitation.name,
                address = invitation.address,
                hasPlusOne = invitation.hasPlusOne,
                onConfirm = { invitationViewModel.onSubmit() },
                nameHasErrors = invitationViewModel.nameHasErrors,
                addressHasErrors = invitationViewModel.addressHasErrors,
                onBackButtonClicked = { navController.popBackStack() },
                onNameFieldChanged = invitationViewModel::handleNameFieldChange,
                onAddressFieldChanged = invitationViewModel::handleAddressFieldChange,
                onPlusOneChanged = invitationViewModel::handlePlusOneChange
            )

            InvitationFormScreen(formState)
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object InvitationForm : Screen("invitationForm")
}