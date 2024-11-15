package ar.edu.unlam.mobile.scaffolding.ui.tuit.create

import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

data class CreateTuitState(
    val createTuitState: UIState<Unit> = UIState.Loading,
    val showExitDialog: Boolean = false,
    val saveDraftState: UIState<Unit> = UIState.Loading,
    val deleteDraftState: UIState<Unit> = UIState.Loading,
)
