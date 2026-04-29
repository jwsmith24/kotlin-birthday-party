package com.example.kotlinbirthdayparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.ui.NavGraph
import com.example.kotlinbirthdayparty.ui.screens.MainScreen
import com.example.kotlinbirthdayparty.ui.screens.MainScreenViewModel
import com.example.kotlinbirthdayparty.ui.screens.NewInviteViewModel
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme

class MainActivity : ComponentActivity() {

    // GUI entry point
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Support classes:
        val invitationRepository = InvitationRepository()


        // Create view models here:
        val mainViewModel = MainScreenViewModel(invitationRepository)
        val newViewModel = NewInviteViewModel(invitationRepository)

        enableEdgeToEdge()
        setContent {
            KotlinBirthdayPartyTheme {
                NavGraph(
                    mainVM = mainViewModel,
                    newVM = newViewModel
                )
            }
        }
    }
}