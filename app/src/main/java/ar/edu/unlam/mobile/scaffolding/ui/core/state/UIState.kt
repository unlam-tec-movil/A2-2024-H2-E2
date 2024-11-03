package ar.edu.unlam.mobile.scaffolding.ui.core.state

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}

fun <T> UIState<T>.onSuccess(action: (T) -> Unit): UIState<T> {
    if (this is UIState.Success) {
        action(data)
    }
    return this
}

fun <T> UIState<T>.onError(action: (String) -> Unit): UIState<T> {
    if (this is UIState.Error) {
        action(message)
    }
    return this
}

fun <T> UIState<T>.onLoading(action: () -> Unit): UIState<T> {
    if (this is UIState.Loading) {
        action()
    }
    return this
}
