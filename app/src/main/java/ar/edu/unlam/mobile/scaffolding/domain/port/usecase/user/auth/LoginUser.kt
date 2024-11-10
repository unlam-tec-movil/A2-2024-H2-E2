package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth

import ar.edu.unlam.mobile.scaffolding.domain.model.AuthToken
import ar.edu.unlam.mobile.scaffolding.domain.model.LoginCredentials

interface LoginUser {
    suspend operator fun invoke(credentials: LoginCredentials): Result<AuthToken>
}
