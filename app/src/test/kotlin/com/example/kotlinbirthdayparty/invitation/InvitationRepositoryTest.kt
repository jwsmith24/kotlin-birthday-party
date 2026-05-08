package com.example.kotlinbirthdayparty.invitation

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class InvitationRepositoryTest {

    private lateinit var invitationRepo: InvitationRepository
    private lateinit var testInvitation: Invitation

    @Before
    fun setup() {
        invitationRepo = InvitationRepository()
        testInvitation = Invitation(id = 10, name = "Yasmeen", address = "123 Main st.")
    }

    @Test
    fun whenUpsertCalledWithNewInvitation_thenAddToList() {
        invitationRepo.upsert(testInvitation)

        assertEquals(4, invitationRepo.sentInvitations.value.size)
        assertEquals("Yasmeen", invitationRepo.sentInvitations.value[3].name)
    }

    @Test
    fun whenUpsertCalledWithExistingInvitation_thenEntryUpdated() {
        invitationRepo.upsert(testInvitation)
        assertEquals(4, invitationRepo.sentInvitations.value.size)
        assertEquals(RsvpStatus.Pending, invitationRepo.sentInvitations.value.first().rsvpStatus)

        val targetInvitation = invitationRepo.sentInvitations.value.first()
        val updatedInvitation = targetInvitation.copy(rsvpStatus = RsvpStatus.Accepted)
        invitationRepo.upsert(updatedInvitation)

        assertEquals(4, invitationRepo.sentInvitations.value.size)
        assertEquals(RsvpStatus.Accepted, invitationRepo.sentInvitations.value.first().rsvpStatus)
    }

    @Test
    fun whenRemoveCalledWithExistingId_thenEntryRemoved() {
        val targetId = 0
        invitationRepo.upsert(testInvitation)
        assertEquals(4, invitationRepo.sentInvitations.value.size)

        invitationRepo.remove(targetId)

        assertEquals(3, invitationRepo.sentInvitations.value.size)
    }

    @Test
    fun whenRemoveCalledWithNonExistingId_thenListUnchanged() {
        val targetId = 99
        invitationRepo.upsert(testInvitation)
        assertEquals(4, invitationRepo.sentInvitations.value.size)

        invitationRepo.remove(targetId)
        assertEquals(4, invitationRepo.sentInvitations.value.size)
    }

    @Test
    fun whenToggleCalled_ToggleStatus() {
        invitationRepo.upsert(testInvitation)
        assertEquals(4, invitationRepo.sentInvitations.value.size)

        invitationRepo.toggle(3)
        assertEquals(RsvpStatus.Accepted, invitationRepo.sentInvitations.value.last().rsvpStatus)
    }
}