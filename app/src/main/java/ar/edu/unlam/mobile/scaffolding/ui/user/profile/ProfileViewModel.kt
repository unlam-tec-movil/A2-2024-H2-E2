package ar.edu.unlam.mobile.scaffolding.ui.user.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.GetProfile
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val getProfile: GetProfile,
    ) : ViewModel() {
        private val _state = MutableStateFlow(ProfileState())
        val state = _state.asStateFlow()

        init {
            loadUserProfile()
        }

        private fun loadUserProfile() {
            viewModelScope.launch {
                try {
                    val profile = getProfile()
                    _state.value =
                        _state.value.copy(
                            profileState = UIState.Success(profile),
                        )
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            profileState = UIState.Error(e.message ?: "Error al cargar el perfil"),
                        )
                }
            }
        }
    }
