package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onAddInvite: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag("mainHeader"),
                title = {
                    Row() {
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
                    } },
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
        // Column of existing invites would go here.
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    KotlinBirthdayPartyTheme() {
        MainScreen {  }
    }
}