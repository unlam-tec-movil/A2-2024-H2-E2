package ar.edu.unlam.mobile.scaffolding.ui.tuit.draft

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import ar.edu.unlam.mobile.scaffolding.ui.components.TuitDraftFeed
import ar.edu.unlam.mobile.scaffolding.ui.core.component.loading.LoadingIndicator
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onLoading
import ar.edu.unlam.mobile.scaffolding.ui.core.state.onSuccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraftTuitScreen(
    modifier: Modifier = Modifier,
    viewModel: DraftTuitViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        TopAppBar(
            title = { Text(text = stringResource(R.string.drafts)) },
            navigationIcon = {
                IconButton(onClick = onDismissRequest) {
                    Icon(Icons.Default.Close, contentDescription = stringResource(R.string.close))
                }
            },
        )
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
