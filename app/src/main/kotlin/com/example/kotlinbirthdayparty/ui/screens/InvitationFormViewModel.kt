package com.example.kotlinbirthdayparty.ui.screens

import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class InvitationFormViewModel(
    private val repo: InvitationRepository
): ViewModel {

    // Later on we can build update functionality too
    private val _invitationFormData = MutableStateFlow(
        Invitation(
            name = "",
            hasPlusOne = false,
            rsvpStatus = RsvpStatus.Pending
        )
    )

    val invitation: StateFlow<Invitation> = _invitationFormData

    fun handleNameFieldChange(name: String) {
        _invitationFormData.update { it.copy(name = name) }
    }

    fun handlePlusOneChange(hasPlusOne: Boolean) {
        _invitationFormData.update { it.copy(hasPlusOne = hasPlusOne) }
    }

    private fun isFormValid(): Boolean {
        val formData = _invitationFormData.value

        return formData.name.isNotBlank()
    }

    fun onSubmit(): Boolean {
        if (isFormValid()) {
            repo.upsert(invitation.value)
            return true
        }

        return false
    }

    override fun onLoad() {
        // do things
    }

    override fun onUnload() {
        // stop doing things
    }
}