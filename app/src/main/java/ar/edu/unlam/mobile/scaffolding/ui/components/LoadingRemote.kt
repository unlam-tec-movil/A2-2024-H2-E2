package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun LazyLoadingRemote(
    loadingRemote: () -> Unit,
    isLoadingRemote: Boolean,
    state: LazyListState,
) {
    val shouldLoadMore =
        remember {
            derivedStateOf {
                val lastVisibleItem =
                    state
                        .layoutInfo
                        .visibleItemsInfo
                        .lastOrNull()
                        ?: return@derivedStateOf false
                lastVisibleItem.index >= (state.layoutInfo.totalItemsCount - 10) && !isLoadingRemote
            }
    }

    val isLoadingRemoteState = remember { derivedStateOf { isLoadingRemote } }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect {
                if (it) {
                    loadingRemote()
                }
            }
    }

    if (isLoadingRemoteState.value) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
