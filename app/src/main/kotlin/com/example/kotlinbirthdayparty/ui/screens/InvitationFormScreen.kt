package com.example.kotlinbirthdayparty.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinbirthdayparty.ui.theme.KotlinBirthdayPartyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class InvitationFormState(
    val name: String,
    val address: String,
    val hasPlusOne: Boolean,
    val onNameFieldChanged: (String) -> Unit,
    val onAddressFieldChanged: (String) -> Unit,
    val onPlusOneChanged: (Boolean) -> Unit,
    val nameHasErrors: StateFlow<Boolean>,
    val addressHasErrors: StateFlow<Boolean>,
    val onConfirm: () -> Boolean,
    val onBackButtonClicked: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitationFormScreen(state: InvitationFormState) {
    val errorName = state.nameHasErrors.collectAsState()
    val errorAddress = state.addressHasErrors.collectAsState()

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
                            onClick = state.onBackButtonClicked,
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
                val context = LocalContext.current

                OutlinedButton(
                    onClick = {
                        val result = state.onConfirm.invoke()
                        if (result) {
                            Toast.makeText(
                                context, "Invite sent!",
                                Toast.LENGTH_SHORT
                            ).show()
                            state.onBackButtonClicked()
                        }
                    },
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
                value = state.name,
                label = { Text(text = "name") },
                isError = errorName.value,
                onValueChange = state.onNameFieldChanged,
                supportingText = {
                    if (errorName.value) {
                        Text("Please fill out name correctly")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("inputName"),
            )

            OutlinedTextField(
                value = state.address,
                label = { Text(text = "address") },
                isError = errorAddress.value,
                onValueChange = state.onAddressFieldChanged,
                supportingText = {
                    if (errorAddress.value) {
                        Text("Please fill out address correctly")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("inputAddress"),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.hasPlusOne,
                    onCheckedChange = state.onPlusOneChanged,
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
            InvitationFormState(
                name = "bob",
                address = "123 Main st.",
                hasPlusOne = true,
                onNameFieldChanged = { },
                onAddressFieldChanged = { },
                onPlusOneChanged = { },
                nameHasErrors = MutableStateFlow(true),
                addressHasErrors = MutableStateFlow(true),
                onConfirm = { false },
                onBackButtonClicked = {},
            )
        )
    }
}