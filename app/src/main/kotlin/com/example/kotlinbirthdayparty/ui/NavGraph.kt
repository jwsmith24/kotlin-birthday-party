package com.example.kotlinbirthdayparty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinbirthdayparty.ui.screens.MainScreen
import com.example.kotlinbirthdayparty.ui.screens.MainScreenViewModel
import com.example.kotlinbirthdayparty.ui.screens.InvitationFormScreen
import com.example.kotlinbirthdayparty.ui.screens.InvitationFormViewModel

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
                // VM lifecycle stuff
            }

            Screen.InvitationForm.route -> {
                // VM lifecycle stuff
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

            InvitationFormScreen(
                name = invitation.name,
                hasPlusOne = invitation.hasPlusOne,
                onConfirm = { invitationViewModel.onSubmit() },
                nameHasErrors = invitationViewModel.nameHasErrors,
                onBackButtonClicked = { navController.popBackStack() },
                onNameFieldChanged = invitationViewModel::handleNameFieldChange,
                onPlusOneChanged = invitationViewModel::handlePlusOneChange
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object InvitationForm : Screen("invitationForm")
}