package com.example.kotlinbirthdayparty.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinbirthdayparty.ui.screens.MainScreen
import com.example.kotlinbirthdayparty.ui.screens.MainScreenViewModel

@Composable
fun NavGraph(
    mainVM: MainScreenViewModel,
    newVM: NewInvitationViewModel
) {

    val navController = rememberNavController()

    navController.addOnDestinationChangedListener { _, destination, _ ->
        when(destination) {
            Screen.Main -> {
                // VM lifecycle stuff
            }

            Screen.New -> {
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
                cards = mainVM.invites,
                onAddInvite = { navController.navigate(Screen.New.route) }
            )
        }

        composable(Screen.New.route) {
            NewInviteScreen(

            )
        }
    }
}

sealed class Screen(val route: String) {
    object Main: Screen("main")
    object New: Screen("new")
}