package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class InvitationFormViewModel(
    private val repo: InvitationRepository
): ViewModel {

    private val _invitationFormData = MutableStateFlow(
        Invitation(
            name = "",
            hasPlusOne = false,
            rsvpStatus = RsvpStatus.Pending
        )
    )

    val invitation: StateFlow<Invitation> = _invitationFormData

    private val _nameHasErrors = MutableStateFlow(false)

    val nameHasErrors: StateFlow<Boolean> = _nameHasErrors

    fun handleNameFieldChange(name: String) {
        _invitationFormData.update { it.copy(name = name) }
    }

    fun handlePlusOneChange(hasPlusOne: Boolean) {
        _invitationFormData.update { it.copy(hasPlusOne = hasPlusOne) }
    }

    fun onSubmit(): Boolean {
        if (_invitationFormData.value.name.isNotBlank()) {
            repo.upsert(invitation.value)

            return true
        } else {
            _nameHasErrors.update { true }
            return false
        }
    }

    override fun onLoad() {
        // do things
    }

    override fun onUnload() {
        // stop doing things
    }
}