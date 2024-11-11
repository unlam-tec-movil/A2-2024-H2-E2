package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.remote.api.AuthApi
import ar.edu.unlam.mobile.scaffolding.data.remote.dto.request.LoginRequest
import ar.edu.unlam.mobile.scaffolding.data.remote.dto.request.RegisterRequest
import ar.edu.unlam.mobile.scaffolding.data.remote.interceptor.TokenManager
import ar.edu.unlam.mobile.scaffolding.data.remote.mapper.AuthMapper
import ar.edu.unlam.mobile.scaffolding.domain.model.AuthToken
import ar.edu.unlam.mobile.scaffolding.domain.model.LoginCredentials
import ar.edu.unlam.mobile.scaffolding.domain.model.RegisterCredentials
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepository
    @Inject
    constructor(
        private val api: AuthApi,
        private val authMapper: AuthMapper,
        private val tokenManager: TokenManager,
    ) : AuthRepository {
        override suspend fun login(credentials: LoginCredentials): Result<AuthToken> {
            return try {
                val request =
                    LoginRequest(
                        email = credentials.email,
                        password = credentials.password,
                    )

                val response = api.login(request)

                val token = authMapper.toAuthToken(response)

                tokenManager.saveToken(token.token)

                Result.success(token)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

        override suspend fun register(credentials: RegisterCredentials): Result<AuthToken> {
            return try {
                val request =
                    RegisterRequest(
                        name = credentials.name,
                        email = credentials.email,
                        password = credentials.password,
                    )

                val response = api.register(request)

                val token = authMapper.toAuthToken(response)

                tokenManager.saveToken(token.token)

                Result.success(token)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

        override fun getToken(): AuthToken? {
            val savedToken = tokenManager.getToken()
            return if (savedToken != null) {
                AuthToken(savedToken)
            } else {
                null
            }
        }

        override fun saveToken(token: AuthToken) {
            tokenManager.saveToken(token.token)
        }

        override fun clearToken() {
            tokenManager.clearToken()
        }
    }
