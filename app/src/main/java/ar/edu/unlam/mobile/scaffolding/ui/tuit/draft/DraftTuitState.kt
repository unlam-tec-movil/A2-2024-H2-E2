package ar.edu.unlam.mobile.scaffolding.ui.tuit.draft

import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

data class DraftTuitState(
    val draftTuitState: UIState<Tuit> = UIState.Loading,
)
