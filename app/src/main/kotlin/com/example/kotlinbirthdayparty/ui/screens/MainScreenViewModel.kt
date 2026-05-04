package com.example.kotlinbirthdayparty.ui.screens

import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationMetrics
import com.example.kotlinbirthdayparty.invitation.InvitationRepository
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainScreenViewModel(
    private val repo: InvitationRepository,
) : ViewModel {
    private var viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    val invites: StateFlow<List<Invitation>> = repo.sentInvitations

    val metrics: StateFlow<InvitationMetrics> = repo.sentInvitations
    // map transforms the data coming in from the repo into the shape we want for the UI
        .map { invites ->
            InvitationMetrics(
                total = invites.size,
                accepted = invites.count { it.rsvpStatus is RsvpStatus.Accepted },
                declined = invites.count { it.rsvpStatus is RsvpStatus.Declined },
                pending = invites.count { it.rsvpStatus is RsvpStatus.Pending }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = InvitationMetrics()
        )
    /*

     stateIn ensures the transformed flow is still hot (repo could give a cold or hot flow)
      - sets how long the viewmodel will listen to the repo if there aren't any changes
      - sets an initial state so the UI has something to render even if the repo hasn't emitted anything yet

     */


    fun onDelete(id: Int) {
        repo.remove(id)
    }

    fun onToggle(id: Int) {
        repo.toggle(id)
    }

    override fun onLoad() {
        // start the vm scope
        viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    }

    override fun onUnload() {
        // stop the vm scope
        viewModelScope.cancel()
    }

}