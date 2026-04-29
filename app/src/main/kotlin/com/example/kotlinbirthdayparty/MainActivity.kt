package com.example.kotlinbirthdayparty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.ui.screens.MainScreen
import com.example.kotlinbirthdayparty.ui.screens.MainScreenViewModel
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme

class MainActivity : ComponentActivity() {

    // Support classes:
    private val _invitationRepository = InvitationRepository()


    // Create view models here:
    private val _mainViewModel = MainScreenViewModel(_invitationRepository)


    // GUI entry point
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBirthdayPartyTheme {
                MainScreen(
                    onAddInvite = _mainViewModel::onAddInviteClicked
                )
            }
        }
    }
}