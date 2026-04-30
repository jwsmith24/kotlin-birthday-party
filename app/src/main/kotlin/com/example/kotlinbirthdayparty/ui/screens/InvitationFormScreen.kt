package com.example.kotlinbirthdayparty.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitationFormScreen(
    name: String,
    hasPlusOne: Boolean,
    onNameFieldChanged: (String) -> Unit,
    onPlusOneChanged: (Boolean) -> Unit,
    nameHasErrors: StateFlow<Boolean>,
    onConfirm: () -> Boolean,
    onBackButtonClicked: () -> Unit
) {
    val error = nameHasErrors.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag("formHeader"),
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ElevatedButton(
                            onClick = onBackButtonClicked,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.inversePrimary,
                                contentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.testTag("backButton")
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
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                OutlinedButton(
                    onClick = {
                        val result = onConfirm.invoke()
                        if (result) onBackButtonClicked() },
                    modifier = Modifier
                        .testTag("confirmButton")
                        .padding(horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Confirm"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                value = name,
                label = { Text(text = "name") },
                isError = error.value,
                onValueChange = onNameFieldChanged,
                supportingText = {
                    if (error.value) {
                        Text("Please fill out name correctly")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("inputName"),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasPlusOne,
                    onCheckedChange = onPlusOneChanged,
                    modifier = Modifier.testTag("checkboxPlusOne")
                )
                Text("Bringing plus one")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewInviteScreenPreview() {
    KotlinBirthdayPartyTheme {
        InvitationFormScreen(
            name = "bob",
            hasPlusOne = true,
            onNameFieldChanged = { },
            onPlusOneChanged = { },
            nameHasErrors = MutableStateFlow(true),
            onConfirm = { false },
            onBackButtonClicked = {}
        )
    }
}