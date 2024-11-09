package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.auth

interface LoginUser {
    suspend operator fun invoke(credentials: LoginCredentials): Result<AuthToken>
}
