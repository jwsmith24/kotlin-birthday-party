package com.example.kotlinbirthdayparty.screens

import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.ui.screens.MainScreenViewModel
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MainScreenViewModelTest {

    private val mockRepo = mockk<InvitationRepository>(relaxed = true)

    private val testVM = MainScreenViewModel(mockRepo)

    @Test
    fun whenOnDeleteIsCalled_thenFiresCorrectCallInRepo() {
        every { mockRepo.remove(any()) } just (Runs)

        testVM.onDelete(0)

        verify { mockRepo.remove(any()) }
    }

    @Test
    fun whenOnToggleIsCalled_thenFiresCorrectCallInRepo() {
        every { mockRepo.toggle(any()) } just (Runs)

        testVM.onToggle(0)

        verify { mockRepo.toggle(any()) }
    }
}