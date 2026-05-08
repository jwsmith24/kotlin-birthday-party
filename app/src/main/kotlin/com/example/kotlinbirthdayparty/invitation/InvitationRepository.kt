package com.example.kotlinbirthdayparty.invitation

import com.example.kotlinbirthdayparty.TestHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class InvitationRepository {
    private val _sentInvitations = MutableStateFlow(TestHelper.invitations)
    val sentInvitations: StateFlow<List<Invitation>> = _sentInvitations
    private var idCounter = sentInvitations.value.size

    fun upsert(invitation: Invitation) {
        _sentInvitations.update { currentList ->
            if (currentList.any { it.id == invitation.id }) {
                currentList.map { if (it.id == invitation.id) invitation else it }
            } else {
                currentList + invitation.copy(id = idCounter++)
            }
        }
    }

    fun remove(targetId: Int) {
        _sentInvitations.update { currentList ->
            currentList.filter { it.id != targetId }
        }
    }

    fun toggle(targetId: Int) {
        _sentInvitations.update { currentList ->
            if (currentList.any { it.id == targetId }) {
                currentList.map {
                    if (it.id == targetId) it.copy(
                        rsvpStatus =
                            getNextRSVPStatus(it.rsvpStatus)
                    ) else it
                }
            } else {
                currentList
            }
        }
    }

    fun getNextRSVPStatus(oldStatus: RsvpStatus): RsvpStatus = when (oldStatus) {
        RsvpStatus.Accepted -> RsvpStatus.Declined
        RsvpStatus.Pending -> RsvpStatus.Accepted
        RsvpStatus.Declined -> RsvpStatus.Pending
    }
}