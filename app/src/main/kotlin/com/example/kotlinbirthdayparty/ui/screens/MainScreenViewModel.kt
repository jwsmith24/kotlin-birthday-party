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
    val scope = CoroutineScope(Dispatchers.IO)

    val invites: StateFlow<List<Invitation>> = repo.sentInvitations

    private val _status = MutableStateFlow(intArrayOf())
    val status: StateFlow<IntArray> = _status

    fun onDelete(id: Int) {
        repo.remove(id)
    }

    fun onToggle(id: Int) {
        repo.toggle(id)
    }

    override fun onLoad() {
        scope.launch {
            invites.collect { invitations ->
                _status.update {
                    Log.d("MainScreenVewModel", "collected new state")

                    calculateStatus(invitations)
                }
            }
        }
    }

    override fun onUnload() {
        scope.cancel()
    }

    fun calculateStatus(invitations: List<Invitation>) = intArrayOf(
        invitations.count { it.rsvpStatus == RsvpStatus.Pending },
        invitations.count { it.rsvpStatus == RsvpStatus.Accepted },
        invitations.count { it.rsvpStatus == RsvpStatus.Declined }
    )
}