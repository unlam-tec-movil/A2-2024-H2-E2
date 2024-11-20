package ar.edu.unlam.mobile.scaffolding.ui.user.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.GetProfile
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.UpdateProfile
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
        private val updateProfile: UpdateProfile,
    ) : ViewModel() {
        private val _state = MutableStateFlow(ProfileState())
        val state = _state.asStateFlow()
        private var isRetrying = false

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
                    isRetrying = false
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            profileState = UIState.Error(e.message ?: "Error al cargar el perfil"),
                        )
                }
            }
        }

        fun retryLoadUserProfile() {
            if (!isRetrying) {
                isRetrying = true
                loadUserProfile()
            }
        }

        fun toggleEditProfile() {
            _state.value =
                _state.value.copy(
                    isEditing = !_state.value.isEditing,
                )
        }

        fun updateProfile(
            name: String,
            avatarUrl: String,
            password: String,
        ) {
            viewModelScope.launch {
                try {
                    updateProfile(name, avatarUrl, password)
                    _state.value =
                        _state.value.copy(
                            editProfileState = UIState.Success(Unit),
                        )
                    loadUserProfile()
                } catch (e: Exception) {
                    _state.value =
                        _state.value.copy(
                            editProfileState = UIState.Error(e.message ?: "Error al actualizar el perfil"),
                        )
                }
            }
        }
    }
