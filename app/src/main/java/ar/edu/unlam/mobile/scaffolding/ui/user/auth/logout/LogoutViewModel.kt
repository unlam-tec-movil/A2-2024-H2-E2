package ar.edu.unlam.mobile.scaffolding.ui.user.auth.logout

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel
    @Inject
    constructor(
        private val authRepository: AuthenticationRepository
    ) : ViewModel() {

        fun logout() {
            authRepository.clearToken()
        }
    }
