package ar.edu.unlam.mobile.scaffolding.domain.port.repository

interface AuthRepository {
    suspend fun login(credentials: LoginCredentials): Result<AuthToken>

    suspend fun register(name: String, email: String, password: String): Result<AuthToken>

    fun saveToken(token: AuthToken)

    fun getToken(): AuthToken?

    fun clearToken()
}
