package ar.edu.unlam.mobile.scaffolding.ui.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.components.MainTopAppBar
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitFeed
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
    onNavigateToCreateTuit: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToDrafts: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = state.isRefreshing,
            onRefresh = { viewModel.onRefresh() },
        )

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.feed_title),
                onLogout = onLogout,
                onNavigateToDrafts = onNavigateToDrafts,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreateTuit,
                modifier = Modifier.padding(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_tuit),
                )
            }
        },
    ) { paddingValues ->
        Box(
            modifier =
                modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState),
        ) {
            FeedContent(
                state = state.tuitsState,
                onRetry = { viewModel.onRefresh() },
                modifier = modifier,
            )
            PullRefreshIndicator(
                refreshing = state.isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}

@Composable
private fun FeedContent(
    state: UIState<List<Tuit>>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        state.onSuccess { tuits ->
            TuitFeed(tuits = tuits)
        }
            .onError { message ->
                ErrorView(
                    message = message,
                    onRetry = onRetry,
                )
            }
            .onLoading {
                LoadingIndicator()
            }
    }
}
