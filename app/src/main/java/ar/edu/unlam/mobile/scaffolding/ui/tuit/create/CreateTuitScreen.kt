package ar.edu.unlam.mobile.scaffolding.ui.tuit.create

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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

@Composable
fun CreateTuitScreen(
    viewModel: CreateTuitViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit,
    onCreateSuccess: () -> Unit = onDismissRequest,
) {
    val uiState = viewModel.uiState.value
    val keyboardController = LocalSoftwareKeyboardController.current
    var tuitText by remember { mutableStateOf("") }
    var showSuccessSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        keyboardController?.show()
    }

    LaunchedEffect(showSuccessSnackbar) {
        if (showSuccessSnackbar) {
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
                    IconButton(
                        onClick = { viewModel.createTuit(tuitText) },
                        enabled = tuitText.isNotBlank() && tuitText.length <= 280,
                    ) {
                        Text(stringResource(R.string.publish))
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
