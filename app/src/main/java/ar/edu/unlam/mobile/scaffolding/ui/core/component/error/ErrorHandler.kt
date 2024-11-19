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
    showRetryButton: Boolean,
) {
    error?.let { errorMessage ->
        LaunchedEffect(errorMessage) {
            val snackbarResult = snackbarHostState.showSnackbar(
                message = errorMessage,
                actionLabel = if (showRetryButton) "Reintentar" else null,
                duration = SnackbarDuration.Long,
            )

            if (snackbarResult == SnackbarResult.ActionPerformed && showRetryButton) {
                onRetry()
            }

            onErrorShown()
        }
    }
}
