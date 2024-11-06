package ar.edu.unlam.mobile.scaffolding.ui.user.auth.register

import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

data class RegisterState(
    val registerState: UIState<Unit> = UIState.Loading,
)
