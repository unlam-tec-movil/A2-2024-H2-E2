package ar.edu.unlam.mobile.scaffolding.ui.user.favorite

import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

data class FavoriteUsersState(
    val favoriteUserState: UIState<List<FavoriteUser>> = UIState.Loading,
)
