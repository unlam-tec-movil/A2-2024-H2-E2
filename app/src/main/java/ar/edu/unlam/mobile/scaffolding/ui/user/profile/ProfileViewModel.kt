package ar.edu.unlam.mobile.scaffolding.ui.user.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.GetLocalProfile
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.GetProfile
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile.SaveLocalProfile
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
        private val getLocalProfile: GetLocalProfile,
        private val saveLocalProfile: SaveLocalProfile,
    ) : ViewModel() {
        private val _state = MutableStateFlow(ProfileState())
        val state = _state.asStateFlow()
        var localProfile: Profile? by mutableStateOf(null)

        init {
            loadLocalProfile()
        }

        private fun loadLocalProfile() {
            viewModelScope.launch {
                localProfile = getLocalProfile()
                if (localProfile != null) {
                    _state.value = _state.value.copy(
                        profileState = UIState.Success(localProfile!!)
                    )
                } else {
                    loadUserProfile()
                }
            }
        }

        private fun loadUserProfile() {
            viewModelScope.launch {
                try {
                    val profile = getProfile()
                    _state.value = _state.value.copy(
                        profileState = UIState.Success(profile)
                    )
                    saveLocalProfile(profile)
                    localProfile = profile
                } catch (e: Exception) {
                    _state.value = _state.value.copy(
                        profileState = UIState.Error(e.message ?: "Error al cargar el perfil")
                    )
                }
            }
        }

        fun retryLoadUserProfile() {
            loadUserProfile()
        }
    }
