package com.example.kotlinbirthdayparty.invitation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val sentInvitations: StateFlow<List<Invitation>> = _sentInvitations.asStateFlow()
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