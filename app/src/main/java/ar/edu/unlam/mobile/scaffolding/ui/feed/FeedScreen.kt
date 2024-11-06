package ar.edu.unlam.mobile.scaffolding.ui.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitFeed
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(state.isRefreshing),
        onRefresh = { viewModel.onRefresh() },
    ) {
        FeedContent(
            state = state,
            onRetry = { viewModel.onRefresh() },
            modifier = modifier,
        )
    }
}

@Composable
private fun FeedContent(
    state: FeedState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        state.tuitsState.onSuccess { tuits ->
            TuitFeed(tuits = tuits)
        }.onError { message ->
            ErrorView(
                message = message,
                onRetry = onRetry,
                isRetrying = state.isRefreshing,
            )
        }.onLoading {
            LoadingIndicator()
        }
    }
}
