package com.example.kotlinbirthdayparty.ui.components

import androidx.compose.foundation.layout.Arrangement
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
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.InvitationMetrics
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private val fontSize = 10.sp
private val lineHeight = 12.sp

@Composable
fun StatusCounter(metrics: InvitationMetrics) {


    Column(
        modifier = Modifier
            .testTag("statusCounter")
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Total: ${metrics.total}",
            fontSize = fontSize,
            lineHeight = lineHeight,
            modifier = Modifier
                .semantics { contentDescription = "labelTotal" }
        )
        Text(
            text = "Pending: ${metrics.pending}",
            fontSize = fontSize,
            lineHeight = lineHeight,
            modifier = Modifier
                .semantics { contentDescription = "labelPending" }
        )
        Text(
            text = "Accepted: ${metrics.accepted}",
            fontSize = fontSize,
            lineHeight = lineHeight,
            modifier = Modifier
                .semantics { contentDescription = "labelAccepted" }
        )
        Text(
            text = "Declined: ${metrics.declined}",
            fontSize = fontSize,
            lineHeight = lineHeight,
            modifier = Modifier
                .semantics { contentDescription = "labelDeclined" }
        )
    }
}

@Composable
@Preview
fun StatusCounterPreview() {

    val testMetrics = InvitationMetrics(
        total = 5,
        accepted = 3,
        declined = 1,
        pending = 1
    )

    KotlinBirthdayPartyTheme() {
        StatusCounter(testMetrics)
    }
}