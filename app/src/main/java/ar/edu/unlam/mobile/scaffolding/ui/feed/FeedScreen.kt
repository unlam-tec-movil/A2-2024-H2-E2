package ar.edu.unlam.mobile.scaffolding.ui.feed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.components.MainTopAppBar
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitFeed
import ar.edu.unlam.mobile.scaffolding.ui.core.component.error.ErrorView
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onError
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess
import ar.edu.unlam.mobile.scaffolding.ui.user.auth.logout.LogoutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
    logoutViewModel: LogoutViewModel = hiltViewModel(),
    onNavigateToCreateTuit: () -> Unit,
    onLogout: () -> Unit,
    fromCreateTuit: Boolean = false,
    listState: LazyListState,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isFabVisible by remember { mutableStateOf(true) }
    val density = LocalDensity.current

    LaunchedEffect(listState) {
        var lastScrollOfSet = 0

        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentScrollOffset ->
                with(density) {
                    val deltaDp = (currentScrollOffset - lastScrollOfSet).toDp()
                    val sensitivityDp = 4.dp

                    if (deltaDp > sensitivityDp) {
                        isFabVisible = false
                    } else if (deltaDp < -sensitivityDp) {
                        isFabVisible = true
                    }
                    lastScrollOfSet = currentScrollOffset
                }
            }
    }

    LaunchedEffect(fromCreateTuit) {
        if (fromCreateTuit) {
            viewModel.onRefresh()
        }
    }

    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = state.isRefreshing,
            onRefresh = { viewModel.onRefresh() },
        )

    Scaffold(
        topBar = {
            MainTopAppBar(
                title = stringResource(R.string.feed_title),
                onLogout = {
                    logoutViewModel.logout()
                    onLogout()
                },
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = isFabVisible,
                enter = expandVertically(expandFrom = Alignment.Bottom) + fadeIn(),
                exit = shrinkVertically(shrinkTowards = Alignment.Bottom) + fadeOut(),
            ) {
                FloatingActionButton(
                    onClick = { onNavigateToCreateTuit() },
                    modifier = Modifier.padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.create_tuit),
                    )
                }
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
                userEmail = state.userEmail,
                onRetry = { viewModel.onRefresh() },
                favoriteUsers = state.favoriteUsers,
                onFavoriteClick = { favoriteUser -> viewModel.onFavoriteClick(favoriteUser) },
                modifier = modifier,
                likeAction = { tuitId, isNotLiked -> viewModel.toggleTuitLike(tuitId, isNotLiked) },
                loadMoreFeed = { viewModel.loadMoreFeed() },
                isLoadingMoreTuits = viewModel.isLoadingMoreTuits,
                listState = listState,
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
    userEmail: String,
    onRetry: () -> Unit,
    onFavoriteClick: (FavoriteUser) -> Unit,
    favoriteUsers: Set<FavoriteUser>,
    likeAction: (tuitId: Int, isNotLiked: Boolean) -> Unit = { _, _ -> },
    loadMoreFeed: () -> Unit,
    isLoadingMoreTuits: Boolean,
    listState: LazyListState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        state
            .onSuccess { tuits ->
                TuitFeed(
                    tuits = tuits,
                    userEmail = userEmail,
                    likeAction = likeAction,
                    favoriteUsers = favoriteUsers,
                    onFavoriteClick = onFavoriteClick,
                    loadMoreFeed = loadMoreFeed,
                    isLoadingMoreTuits = isLoadingMoreTuits,
                    listState = listState,
                )
            }.onError { message ->
                ErrorView(
                    message = message,
                    onRetry = onRetry,
                )
            }.onLoading {
                LoadingIndicator()
            }
    }
}
