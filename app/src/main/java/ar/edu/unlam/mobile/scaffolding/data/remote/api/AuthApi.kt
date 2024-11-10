package ar.edu.unlam.mobile.scaffolding.data.remote.api

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.request.LoginRequest
import ar.edu.unlam.mobile.scaffolding.data.remote.dto.request.RegisterRequest
import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.UserResponse
import ar.edu.unlam.mobile.scaffolding.data.remote.util.ApiConfig
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST(ApiConfig.Endpoints.LOGIN)
    suspend fun login(
        @Header(ApiConfig.APPLICATION_TOKEN_HEADER) appToken: String = ApiConfig.APPLICATION_TOKEN_VALUE,
        @Body request: LoginRequest
    ): UserResponse

    @POST(ApiConfig.Endpoints.REGISTER)
    suspend fun register(
        @Header(ApiConfig.APPLICATION_TOKEN_HEADER) appToken: String = ApiConfig.APPLICATION_TOKEN_VALUE,
        @Body request: RegisterRequest
    ): UserResponse
}
