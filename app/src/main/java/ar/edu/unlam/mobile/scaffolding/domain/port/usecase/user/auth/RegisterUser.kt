package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth

import ar.edu.unlam.mobile.scaffolding.domain.model.AuthToken
import ar.edu.unlam.mobile.scaffolding.domain.model.RegisterCredentials
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.AuthRepository
import javax.inject.Inject

class RegisterUser
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        suspend operator fun invoke(credentials: RegisterCredentials): Result<AuthToken> {
            return authRepository.register(credentials)
        }
    }
