package com.example.kotlinbirthdayparty.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinbirthdayparty.TestHelper
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun StatusCounter(data: StateFlow<List<Invitation>>) {

    val myData by data.collectAsState()

    val numPending = myData.count { it.rsvpStatus == RsvpStatus.Pending }
    val numAccepted = myData.count { it.rsvpStatus == RsvpStatus.Accepted }
    val numDeclined = myData.count { it.rsvpStatus == RsvpStatus.Declined }

    Column(
        modifier = Modifier
            .testTag("statusCounter")
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = "Pending: $numPending",
            fontSize = 10.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .semantics { contentDescription = "labelPending" }
        )
        Text(
            text = "Accepted: $numAccepted",
            fontSize = 10.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .semantics { contentDescription = "labelAccepted" }
        )
        Text(
            text = "Declined: $numDeclined",
            fontSize = 10.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .semantics { contentDescription = "labelDeclined" }
        )
    }
}

@Composable
@Preview
fun StatusCounterPreview() {

    val testInvites = MutableStateFlow(TestHelper.invitations)

    KotlinBirthdayPartyTheme() {
        StatusCounter(testInvites)
    }
}