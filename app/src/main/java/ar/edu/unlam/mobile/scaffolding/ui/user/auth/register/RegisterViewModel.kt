package ar.edu.unlam.mobile.scaffolding.ui.user.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.RegisterCredentials
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
    ) : ViewModel() {
        private val _state = MutableStateFlow(RegisterState())
        val state = _state.asStateFlow()
        private var name = ""
        private var email = ""
        private var password = ""

        fun onPasswordChange(newPassword: String) {
            password = newPassword
        }

        fun onEmailChange(newEmail: String) {
            email = newEmail
        }

        fun onNameChange(newName: String) {
            name = newName
        }

        fun register() {
            val errorMessage = validateCredentials()
            if (errorMessage != null) {
                _state.update {
                    it.copy(
                        registerState = UIState.Error(errorMessage),
                    )
                }
            }
            _state.update {
                it.copy(
                    registerState = UIState.Loading,
                )
            }
            viewModelScope.launch {
                val userCredentials = RegisterCredentials(name, email, password)
                val result = registerUser(userCredentials)
                if (result.isSuccess) {
                    _state.update {
                        it.copy(
                            registerState = UIState.Success(Unit),
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            registerState = UIState.Error("No se pudo registrar el usuario."),
                        )
                    }
                }
            }
        }

        private fun validateCredentials(): String? =
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                "Por favor, complete todos los campos."
            } else if (!isValidEmail(email)) {
                "Por favor, ingrese un email válido."
            } else if (password.length < 6) {
                "La contraseña debe tener al menos 6 caracteres."
            } else {
                null
            }

        private fun isValidEmail(email: String): Boolean {
            val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
            return emailRegex.matches(email)
        }

        fun getName(): String = name

        fun getEmail(): String = email

        fun getPassword(): String = password
    }
