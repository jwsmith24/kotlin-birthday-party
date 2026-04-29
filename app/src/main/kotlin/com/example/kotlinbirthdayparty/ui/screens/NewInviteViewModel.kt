package com.example.kotlinbirthdayparty.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewInviteViewModel(
    private val repo: InvitationRepository
) : ViewModel() {

    // Later on we can build update functionality too
    private val _i = MutableStateFlow(
        Invitation(
            name = "",
            hasPlusOne = false,
            rsvpStatus = RsvpStatus.Pending
        )
    )

    val i: StateFlow<Invitation> = _i

    fun handleNameFieldChange(s: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _i.update { it.copy(name = s) }
        }
    }

    fun handlePlusOneChange(b: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            _i.update { it.copy(hasPlusOne = b) }
        }
    }

    fun onSubmit(): Boolean {
        if (i.value.name.isNotBlank()) {
            repo.upsert(i.value)
            return true
        }

        return false
    }
}