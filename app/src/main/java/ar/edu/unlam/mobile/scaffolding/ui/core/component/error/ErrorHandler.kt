package ar.edu.unlam.mobile.scaffolding.ui.core.component.error

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ErrorHandler(
    error: String?,
    onRetry: () -> Unit,
    snackbarHostState: SnackbarHostState,
    onErrorShown: () -> Unit,
) {
    error?.let { errorMessage ->
        LaunchedEffect(errorMessage) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                actionLabel = "Reintentar",
                duration = SnackbarDuration.Long,
            ).let { result ->
                if (result == SnackbarResult.ActionPerformed) {
                    onRetry()
                }
                onErrorShown()
            }
        }
    }
}
