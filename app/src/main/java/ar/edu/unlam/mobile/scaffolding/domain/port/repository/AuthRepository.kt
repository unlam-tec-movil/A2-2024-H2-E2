package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.AuthToken
import ar.edu.unlam.mobile.scaffolding.domain.model.LoginCredentials
import ar.edu.unlam.mobile.scaffolding.domain.model.RegisterCredentials

interface AuthRepository {
    suspend fun login(credentials: LoginCredentials): Result<AuthToken>

    suspend fun register(credentials: RegisterCredentials): Result<AuthToken>

    fun saveToken(token: AuthToken)

    fun getToken(): AuthToken?

    fun clearToken()
}
