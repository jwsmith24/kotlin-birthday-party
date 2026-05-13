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

    private val _invitationFormData = MutableStateFlow(
        Invitation(
            name = "",
            hasPlusOne = false,
            address = "",
            time = "0:00",
            rsvpStatus = RsvpStatus.Pending
        )
    )

    val invitation: StateFlow<Invitation> = _invitationFormData

    private val _nameHasErrors = MutableStateFlow(false)
    val nameHasErrors: StateFlow<Boolean> = _nameHasErrors

    private val _addressHasErrors = MutableStateFlow(false)
    val addressHasErrors: StateFlow<Boolean> = _addressHasErrors

    fun handleNameFieldChange(name: String) {
        _invitationFormData.update { it.copy(name = name) }
    }

    fun handleAddressFieldChange(address: String) {
        _invitationFormData.update { it.copy(address = address) }
    }

    fun handlePlusOneChange(hasPlusOne: Boolean) {
        _invitationFormData.update { it.copy(hasPlusOne = hasPlusOne) }
    }

    fun onSubmit(): Boolean {

        val nameIsValid = _invitationFormData.value.name.isNotBlank()
        val addressIsValid = _invitationFormData.value.address.isNotBlank()

        if (nameIsValid && addressIsValid) {
            repo.upsert(invitation.value)

            return true
        } else {
            _nameHasErrors.update { !nameIsValid }
            _addressHasErrors.update { !addressIsValid }
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