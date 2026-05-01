package com.example.kotlinbirthdayparty.ui.screens

import android.util.Log
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repo: InvitationRepository,
) : ViewModel {
    val invites: StateFlow<List<Invitation>> = repo.sentInvitations


    fun onDelete(id: Int) {
        repo.remove(id)
    }

    fun onToggle(id: Int) {
        repo.toggle(id)
    }

    override fun onLoad() {
    }

    override fun onUnload() {
    }
}