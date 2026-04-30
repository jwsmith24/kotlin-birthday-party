package com.example.kotlinbirthdayparty.invitation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class InvitationRepository {
    private val _sentInvitations = MutableStateFlow(
        listOf(
            Invitation(
                id = 0,
                name = "Jacob Varner",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                id = 1,
                name = "Curt Arbtin",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Accepted
            ),
            Invitation(
                id = 2,
                name = "Rob Payne",
                hasPlusOne = false,
                rsvpStatus = RsvpStatus.Declined
            )
        )
    )
    val sentInvitations: StateFlow<List<Invitation>> = _sentInvitations
    private var idCounter = 3

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
}