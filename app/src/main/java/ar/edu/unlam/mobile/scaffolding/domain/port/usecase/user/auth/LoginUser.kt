package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth

import ar.edu.unlam.mobile.scaffolding.domain.model.AuthToken
import ar.edu.unlam.mobile.scaffolding.domain.model.LoginCredentials
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.AuthRepository
import javax.inject.Inject

class LoginUser
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        suspend operator fun invoke(credentials: LoginCredentials): Result<AuthToken> {
            return authRepository.login(credentials)
        }
    }
