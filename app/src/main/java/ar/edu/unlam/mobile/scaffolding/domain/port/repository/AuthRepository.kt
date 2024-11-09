package ar.edu.unlam.mobile.scaffolding.domain.port.repository

interface AuthRepository {
    suspend fun login(credentials: LoginCredentials): Result<AuthToken>

    fun saveToken(token: AuthToken)

    fun getToken(): AuthToken?

    fun clearToken()
}
