package com.example.kotlinbirthdayparty.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun InvitationCard(cardData: StateFlow<Invitation>, onDelete: (Int) -> Unit) {

    val invitationData = cardData.collectAsState()

    val myColor: Color = when(invitationData.value.rsvpStatus) {
        RsvpStatus.Pending -> Color.Blue
        RsvpStatus.Accepted -> Color.Green
        RsvpStatus.Declined -> Color.Red
    }

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .testTag("inviteCard" + invitationData.value.id)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                Icons.Default.Adb,
                contentDescription = "droidIcon",
                modifier = Modifier.padding(horizontal = 5.dp)
            )

            Column(
            ) {
                Text(
                    text = invitationData.value.name,
                    fontWeight = FontWeight(500),
                    fontSize = 20.sp,
                    modifier = Modifier.testTag("name")
                )
                if (invitationData.value.hasPlusOne) {
                    Text(
                        text = "Plus one",
                        modifier = Modifier.testTag("plusOne")
                        )
                }

                Text(
                    text = ("Status: " + invitationData.value.rsvpStatus::class.simpleName) ?: "",
                    color = myColor,
                    modifier = Modifier.testTag("rsvpStatus")
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun InvitationCardPendingPreview() {

    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        InvitationCard(
            MutableStateFlow(
                Invitation(
                    0,
                    name = "Jake Smith",
                    hasPlusOne = true,
                    rsvpStatus = RsvpStatus.Pending,
                )
            )
        ) { }

        InvitationCard(
            MutableStateFlow(
                Invitation(
                    0,
                    name = "Curt Arbtin",
                    hasPlusOne = true,
                    rsvpStatus = RsvpStatus.Accepted,
                )
            )
        ) { }

        InvitationCard(
            MutableStateFlow(
                Invitation(
                    0,
                    name = "Rob Payne",
                    hasPlusOne = true,
                    rsvpStatus = RsvpStatus.Declined,
                )
            )
        ) { }
    }
}