package com.example.kotlinbirthdayparty.screens

import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import com.example.kotlinbirthdayparty.ui.screens.MainScreenViewModel
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Test

class MainScreenViewModelTest {

    private val mockRepo = mockk<InvitationRepository>(relaxed = true)

    private val testVM = MainScreenViewModel(mockRepo)

    @Before
    fun setup() {

        val testInvitations = listOf(
            Invitation(
                name = "Jake Smith",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                name = "Curt Arbtin",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                name = "Rob Payne",
                hasPlusOne = false,
                rsvpStatus = RsvpStatus.Declined
            )
        )

        every { mockRepo.sentInvitations } returns MutableStateFlow(testInvitations)
        every { mockRepo.upsert(any()) } just(Runs)
        every { mockRepo.toggle(any()) } just(Runs)
        every { mockRepo.remove(any()) } just(Runs)
    }

    @Test
    fun whenOnDeleteIsCalled_thenFiresCorrectCallInRepo() {
        testVM.onDelete(0)

        verify { mockRepo.remove(any()) }
    }

    @Test
    fun whenOnToggleIsCalled_thenFiresCorrectCallInRepo() {
        testVM.onToggle(0)

        verify { mockRepo.toggle(any()) }
    }

    @Test
    fun whenInvitationsNotNull_thenCalculatesStatusCorrectly() {
        val testInvitations = listOf(
            Invitation(
                name = "Jake Smith",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                name = "Curt Arbtin",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                name = "Rob Payne",
                hasPlusOne = false,
                rsvpStatus = RsvpStatus.Declined
            )
        )

        val result = testVM.calculateStatus(testInvitations)

        assertEquals(2, result[0])
        assertEquals(0, result[1])
        assertEquals(1, result[2])
    }
}