package ar.edu.unlam.mobile.scaffolding.data.network

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {

    @POST("/v1/users/login")
    suspend fun login(@Body loginRequest: LoginRequest): User

    @POST("/v1/users")
    suspend fun createUser (@Body user: User): User

    @GET("/v1/users/{id}")
    suspend fun getUserByID (@Path("id") userId: String): User
}

data class LoginRequest (
    val email: String,
    val password: String
)