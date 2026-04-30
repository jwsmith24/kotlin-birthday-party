package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Block
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinbirthdayparty.invitation.Invitation
import com.example.kotlinbirthdayparty.invitation.RsvpStatus
import com.example.kotlinbirthdayparty.ui.components.InvitationCard
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    cards: StateFlow<List<Invitation>>,
    onAddInvite: () -> Unit,
) {
    val myCards = cards.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag("mainHeader"),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "awesomeIcon"
                        )
                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )
                        Text(
                            fontWeight = FontWeight(900),
                            text = "nVITE"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddInvite,
                modifier = Modifier.testTag("addInviteButton")
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "addInviteIcon",
                )
            }
        }
    ) { innerPadding ->

        if (myCards.value.size > 0) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .testTag("cardContainer")
            ) {
                items(myCards.value) { card ->
                    InvitationCard(card) { }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Block,
                    contentDescription = "noCardsIcon",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {

    val previewCards = MutableStateFlow(
        listOf(
            Invitation(
                name = "Jake Smith",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Pending
            ),
            Invitation(
                name = "Curt Arbtin",
                hasPlusOne = true,
                rsvpStatus = RsvpStatus.Accepted
            ),
            Invitation(
                name = "Rob Payne",
                hasPlusOne = false,
                rsvpStatus = RsvpStatus.Declined
            )
        )
    )

    KotlinBirthdayPartyTheme{
        MainScreen(previewCards) { }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenEmptyPreview() {
    KotlinBirthdayPartyTheme {
        MainScreen(MutableStateFlow(emptyList())) { }
    }
}