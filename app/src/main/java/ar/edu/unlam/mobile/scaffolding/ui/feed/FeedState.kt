package ar.edu.unlam.mobile.scaffolding.ui.feed

import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

data class FeedState(
    val tuitsState: UIState<List<Tuit>> = UIState.Loading,
    val isRefreshing: Boolean = false,
    val favoriteUsers: Set<FavoriteUser> = emptySet(),
)
