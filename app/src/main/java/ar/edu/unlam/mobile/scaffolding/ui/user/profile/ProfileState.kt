package ar.edu.unlam.mobile.scaffolding.ui.user.profile

import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState

data class ProfileState(
    val profileState: UIState<Profile> = UIState.Loading,
    val editProfileState: UIState<Unit> = UIState.None,
    val isEditing: Boolean = false,
)
