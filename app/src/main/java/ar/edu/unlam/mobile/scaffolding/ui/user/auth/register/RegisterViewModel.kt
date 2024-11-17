package ar.edu.unlam.mobile.scaffolding.ui.user.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.LoginCredentials
import ar.edu.unlam.mobile.scaffolding.domain.model.RegisterCredentials
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth.LoginUser
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth.RegisterUser
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
    @Inject
    constructor(
        private val registerUser: RegisterUser,
        private val loginUser: LoginUser,
    ) : ViewModel() {
        private val _state = MutableStateFlow(RegisterState(registerState = UIState.None))
        val state = _state.asStateFlow()

        fun register(
            name: String,
            email: String,
            password: String,
        ) {
        val errorMessage = validateCredentials(name, email, password)
        if (errorMessage != null) {
            _state.update {
                it.copy(
                    registerState = UIState.Error(errorMessage),
                )
            }
            return
        }

        _state.update {
            it.copy(
                registerState = UIState.Loading,
            )
        }

        viewModelScope.launch {
            val userCredentials = RegisterCredentials(name, email, password)
            val loginCredentials = LoginCredentials(email, password)
            val result = registerUser(userCredentials)

            if (result.isSuccess) {
                _state.update {
                    it.copy(
                        registerState = UIState.Success(Unit),
                    )
                }
                loginUser(loginCredentials)
            } else {
                _state.update {
                    it.copy(
                        registerState =
                        UIState.Error("No se pudo registrar el usuario."),
                    )
                }
            }
        }
    }

    private fun validateCredentials(
        name: String,
        email: String,
        password: String,
    ): String? =
        when {
            name.isBlank() || email.isBlank() || password.isBlank() -> "Por favor, complete todos los campos."
            !isValidEmail(email) -> "Por favor, ingrese un email válido."
            password.length < 6 -> "La contraseña debe tener al menos 6 caracteres."
            else -> null
        }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return emailRegex.matches(email)
    }

    fun clearErrorState() {
        _state.update {
            it.copy(registerState = UIState.None)
        }
    }
}
