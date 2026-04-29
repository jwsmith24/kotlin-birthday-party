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
        testInvitation = Invitation(name = "Curt")
    }

    @Test
    fun whenUpsertCalledWithNewInvitation_thenAddToList() {
        invitationRepo.upsert(testInvitation)

        assertEquals(1, invitationRepo.sentInvitations.value.size)
        assertEquals("Curt", invitationRepo.sentInvitations.value[0].name)
    }

    @Test
    fun whenUpsertCalledWithExistingInvitation_thenEntryUpdated() {
        invitationRepo.upsert(testInvitation)
        assertEquals(1, invitationRepo.sentInvitations.value.size)
        assertEquals(RsvpStatus.Pending, invitationRepo.sentInvitations.value.first().rsvpStatus)

        val targetInvitation = invitationRepo.sentInvitations.value.first()
        val updatedInvitation = targetInvitation.copy(rsvpStatus = RsvpStatus.Accepted)
        invitationRepo.upsert(updatedInvitation)

        assertEquals(1, invitationRepo.sentInvitations.value.size)
        assertEquals(RsvpStatus.Accepted, invitationRepo.sentInvitations.value.first().rsvpStatus)
    }

    @Test
    fun whenRemoveCalledWithExistingId_thenEntryRemoved() {
        val targetId = 0
        invitationRepo.upsert(testInvitation)
        assertEquals(1, invitationRepo.sentInvitations.value.size)

        invitationRepo.remove(targetId)

        assertEquals(0, invitationRepo.sentInvitations.value.size)
    }

    @Test
    fun whenRemoveCalledWithNonExistingId_thenListUnchanged() {
        val targetId = 99
        invitationRepo.upsert(testInvitation)
        assertEquals(1, invitationRepo.sentInvitations.value.size)

        invitationRepo.remove(targetId)
        assertEquals(1, invitationRepo.sentInvitations.value.size)
    }
}