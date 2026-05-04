package com.example.kotlinbirthdayparty.invitation

import androidx.compose.ui.graphics.Color

sealed class RsvpStatus(val color: Color) {
     object Pending : RsvpStatus(color = Color.Yellow)

     object Accepted: RsvpStatus(color = Color.Green)

     object Declined: RsvpStatus(color = Color.Red)
}