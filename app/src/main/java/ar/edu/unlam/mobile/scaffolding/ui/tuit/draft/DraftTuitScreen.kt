package ar.edu.unlam.mobile.scaffolding.ui.tuit.draft

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitDraftFeed
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess

@Composable
fun DraftTuitScreen(
    modifier: Modifier = Modifier,
    viewModel: DraftTuitViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        DraftTuitContent(
            state = state.draftTuitState,
            modifier = modifier,
        )
    }
}

@Composable
private fun DraftTuitContent(
    state: UIState<List<DraftTuit>>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        state.onSuccess { drafts ->
            TuitDraftFeed(drafts = drafts)
        }
            .onLoading {
                LoadingIndicator()
            }
    }
}
