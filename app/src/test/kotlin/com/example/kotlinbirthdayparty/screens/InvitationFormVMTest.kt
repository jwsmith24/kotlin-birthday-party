package com.example.kotlinbirthdayparty.screens

import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.ui.screens.InvitationFormViewModel
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class InvitationFormVMTest() {

    private val mockRepo = mockk<InvitationRepository>()

    private val testVM = InvitationFormViewModel(mockRepo)

    @Test
    fun whenSubmittingBlankName_thenShouldReturnFalseOnSubmit() {

        assertFalse(testVM.onSubmit())

        testVM.handleNameFieldChange("changing name, address still blank")

        assertFalse(testVM.onSubmit())
    }

    @Test
    fun whenSubmittingGoodNameAndAddress_thenShouldReturnCallRepoWithRightData() {
        val inviteSlot = slot<Invitation>()

        every { mockRepo.upsert(
            capture(inviteSlot)
        ) } just(Runs)

        testVM.handleNameFieldChange("Hello")
        testVM.handleAddressFieldChange("World!")
        testVM.handlePlusOneChange(true)


        val submittedInvite = Invitation(
            id = 5,
            name = "Hello",
            address = "World!",
            hasPlusOne = true,
            time = "11:00",
        )

        assertTrue(testVM.onSubmit())

        verify { mockRepo.upsert(any()) }

        assertEquals(submittedInvite, inviteSlot.captured)
    }


}