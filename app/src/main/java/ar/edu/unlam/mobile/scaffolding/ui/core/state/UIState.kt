package ar.edu.unlam.mobile.scaffolding.ui.core.state

import androidx.compose.runtime.Composable

sealed class UIState<out T> {
    data object None : UIState<Nothing>()

    data object Loading : UIState<Nothing>()

    data class Success<T>(val data: T) : UIState<T>()

    data class Error(val message: String) : UIState<Nothing>()
}

@Composable
fun <T> UIState<T>.onSuccess(action: @Composable (T) -> Unit): UIState<T> {
    if (this is UIState.Success) {
        action(data)
    }
    return this
}

@Composable
fun <T> UIState<T>.onError(action: @Composable (String) -> Unit): UIState<T> {
    if (this is UIState.Error) {
        action(message)
    }
    return this
}

@Composable
fun <T> UIState<T>.onLoading(action: @Composable () -> Unit): UIState<T> {
    if (this is UIState.Loading) {
        action()
    }
    return this
}

@Composable
fun <T> UIState<T>.onNone(action: @Composable () -> Unit): UIState<T> {
    if (this is UIState.None) {
        action()
    }
    return this
}
