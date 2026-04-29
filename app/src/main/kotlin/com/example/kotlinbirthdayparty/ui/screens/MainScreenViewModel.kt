package com.example.kotlinbirthdayparty.ui.screens

import androidx.lifecycle.ViewModel
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    private val repo: InvitationRepository,
) : ViewModel() {
    private val _invites = MutableStateFlow<List<Invitation>>(emptyList())
    val invites: StateFlow<List<Invitation>> = _invites

    fun onAddInviteClicked() {
    }
}