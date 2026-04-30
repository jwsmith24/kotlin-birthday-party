package com.example.kotlinbirthdayparty.ui.screens

import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    private val repo: InvitationRepository,
): ViewModel {
    val invites: StateFlow<List<Invitation>> = repo.sentInvitations

    fun onDelete(id: Int) {
        repo.remove(id)
    }

    fun onToggle(id: Int) {
        repo.toggle(id)
    }

    override fun onLoad() {
        // do things
    }

    override fun onUnload() {
        // stop doing things
    }
}