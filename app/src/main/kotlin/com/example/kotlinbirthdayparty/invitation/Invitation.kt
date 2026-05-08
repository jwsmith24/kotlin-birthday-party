package com.example.kotlinbirthdayparty.invitation

data class Invitation(
    val id: Int? = null,
    val name: String,
    val hasPlusOne: Boolean = false,
    val rsvpStatus: RsvpStatus = RsvpStatus.Pending,
    val address: String
)
