package ar.edu.unlam.mobile.scaffolding.ui.user.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.LoginCredentials
import ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth.LoginUser
import ar.edu.unlam.mobile.scaffolding.ui.core.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val loginUser: LoginUser,
    ) : ViewModel() {
        private val _state = MutableStateFlow(LoginState())
        val state = _state.asStateFlow()
        private var email = ""
        private var password = ""

        fun onPasswordChange(newPassword: String) {
            password = newPassword
        }

        fun onEmailChange(newEmail: String) {
            email = newEmail
        }

        fun login() {
            _state.update {
                it.copy(
                    loginState = UIState.Loading,
                )
            }
            viewModelScope.launch {
                val userCredentials = LoginCredentials(email, password)
                val result = loginUser(userCredentials)

                if (result.isSuccess) {
                    _state.update {
                        it.copy(
                            loginState = UIState.Success(Unit),
                        )
                    }
                } else if (result.isFailure) {
                    if (userCredentials.email.isEmpty() || userCredentials.password.isEmpty()) {
                        _state.update {
                            it.copy(
                                loginState = UIState.Error("Por favor, complete todos los campos"),
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                loginState = UIState.Error("Error al iniciar sesión"),
                            )
                        }
                    }
                }
            }
        }

        fun getEmail(): String = email

        fun getPassword(): String = password
    }
