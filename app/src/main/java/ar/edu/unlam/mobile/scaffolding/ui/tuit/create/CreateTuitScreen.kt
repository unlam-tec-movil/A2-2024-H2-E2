package ar.edu.unlam.mobile.scaffolding.ui.tuit.create

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTuitScreen(
    viewModel: CreateTuitViewModel = hiltViewModel(),
    initialText: String = "",
    onDismissRequest: () -> Unit,
    onCreateSuccess: () -> Unit = onDismissRequest,
    onNavigateToDrafts: () -> Unit,
) {
    val uiState = viewModel.uiState.value
    val keyboardController = LocalSoftwareKeyboardController.current
    var tuitText by remember { mutableStateOf(initialText) }
    var showSuccessSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        keyboardController?.show()
    }

    LaunchedEffect(showSuccessSnackbar) {
        if (showSuccessSnackbar && initialText.isNotBlank()) {
            viewModel.deleteDraftAfterPublish(initialText)
            delay(1500L)
            onCreateSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.create_tuit_title)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (tuitText.isBlank()) {
                                onDismissRequest()
                            } else {
                                viewModel.onCloseRequest(tuitText)
                            }
                        },
                    ) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close))
                    }
                },
                actions = {
                    TextButton(
                        onClick = onNavigateToDrafts,
                    ) {
                        Text(
                            text = stringResource(R.string.drafts),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    TextButton(
                        onClick = {
                            viewModel.createTuit(
                                message = tuitText,
                                isFromDraft = initialText.isNotBlank(),
                            )
                        },
                        enabled = tuitText.isNotBlank() && tuitText.length <= 280,
                    ) {
                        Text(
                            text = stringResource(R.string.publish),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                OutlinedTextField(
                    value = tuitText,
                    onValueChange = {
                        tuitText = it
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                    placeholder = { Text(stringResource(R.string.create_tuit_hint)) },
                )
            }

            uiState.createTuitState
                .onLoading {
                    LoadingIndicator()
                }
                .onSuccess {
                    showSuccessSnackbar = true
                }
                .onError { message ->
                    ErrorView(
                        message = message,
                        onRetry = { viewModel.createTuit(tuitText) },
                    )
                }

            uiState.saveDraftState
                .onSuccess {
                    LaunchedEffect(Unit) {
                        onDismissRequest()
                    }
                }
                .onError { message ->
                    ErrorView(
                        message = message,
                        onRetry = { viewModel.saveDraft(tuitText) },
                    )
                }
        }
    }

    if (uiState.showExitDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissExitDialog() },
            title = { Text(stringResource(R.string.save_draft_title)) },
            text = { Text(stringResource(R.string.save_draft_message)) },
            confirmButton = {
                TextButton(
                    onClick = { viewModel.saveDraft(tuitText) },
                ) {
                    Text(stringResource(R.string.save_draft))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.dismissExitDialog()
                        onDismissRequest()
                    },
                ) {
                    Text(stringResource(R.string.discard))
                }
            },
        )
    }

    if (showSuccessSnackbar) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            action = {
                TextButton(onClick = { showSuccessSnackbar = false }) {
                    Text(stringResource(R.string.dismiss))
                }
            },
        ) {
            Text(stringResource(R.string.tuit_created))
        }
    }
}
