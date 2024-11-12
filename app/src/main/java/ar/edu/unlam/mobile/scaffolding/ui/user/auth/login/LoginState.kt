package ar.edu.unlam.mobile.scaffolding.ui.user.auth.login

import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

data class LoginState(
    val loginState: UIState<Unit> = UIState.Loading,
)
