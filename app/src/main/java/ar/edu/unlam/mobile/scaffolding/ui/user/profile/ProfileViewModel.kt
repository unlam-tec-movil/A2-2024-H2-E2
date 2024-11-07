package ar.edu.unlam.mobile.scaffolding.ui.user.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.repository.ProfileRepository
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val profileRepository: ProfileRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(ProfileState())
        val state = _state.asStateFlow()

        init {
            viewModelScope.launch {
                try {
                    _state.update {
                        it.copy(
                            profileState = UIState.Loading,
                        )
                    }
                    val profile = profileRepository.getProfile()
                    _state.update {
                        it.copy(
                            profileState =
                                UIState.Success(profile),
                        )
                    }
                } catch (e: Exception) {
                    _state.update {
                        it.copy(
                            profileState =
                                UIState.Error(
                                    e.message ?: "Hubo un error",
                                ),
                        )
                    }
                }
            }
        }
    }
