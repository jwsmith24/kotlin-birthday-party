package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitationFormScreen(
    name: String,
    hasPlusOne: Boolean,
    onNameFieldChanged: (String) -> Unit,
    onPlusOneChanged: (Boolean) -> Unit,
    onSubmit: () -> Boolean,
    onBackButtonClicked: () -> Unit
) {
    // todo: wire up form data
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag("newHeader"),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OutlinedButton(
                            onClick = onBackButtonClicked
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "backIcon"
                            )
                        }
                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )
                        Text(
                            fontWeight = FontWeight(900),
                            text = "Add New Invitation"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                value = "",
                label = { Text(text = "name") },
                isError = false,
                onValueChange = onNameFieldChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = onPlusOneChanged,
                )
                Text("Bringing plus one")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewInviteScreenPreview() {
    InvitationFormScreen(
        name = "bob",
        hasPlusOne = true,
        onNameFieldChanged = { },
        onPlusOneChanged = { },
        onSubmit = { false },
        onBackButtonClicked = {}
    )
}