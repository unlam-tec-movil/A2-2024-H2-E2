package ar.edu.unlam.mobile.scaffolding.ui.user.auth.logout

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) : ViewModel() {
        fun logout() {
            authRepository.clearToken()
        }
    }
