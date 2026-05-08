package com.example.kotlinbirthdayparty

import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.RsvpStatus

object TestHelper {

    val invitations = listOf(
        Invitation(
            id = 0,
            name = "Jake Smith",
            address = "123 Main st.",
            hasPlusOne = true,
        ),
        Invitation(
            id = 1,
            name = "Curt Arbtin",
            address = "123 Main st.",
            hasPlusOne = true,
            rsvpStatus = RsvpStatus.Accepted,
        ),
        Invitation(
            id = 2,
            name = "Rob Payne",
            address = "123 Main st.",
            rsvpStatus = RsvpStatus.Declined,
        )
    )
}