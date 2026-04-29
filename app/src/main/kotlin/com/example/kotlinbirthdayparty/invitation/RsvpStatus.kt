package com.example.kotlinbirthdayparty.invitation

sealed class RsvpStatus {
    object Pending : RsvpStatus()

    object Accepted: RsvpStatus()

    object Declined: RsvpStatus()
}