package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.network.LoginRequest
import ar.edu.unlam.mobile.scaffolding.data.network.UserApiService
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import ar.edu.unlam.mobile.scaffolding.domain.user.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUserRepository : UserRepository {
    private val apiService: UserApiService by lazy{
        Retrofit
            .Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)

    }
    override suspend fun login(email: String, password: String): User {
        val loginRequest =  LoginRequest(email, password)
        return apiService.login(loginRequest)
    }

    override suspend fun createUser(user: User): User {
        return apiService.createUser(user)
    }

    override suspend fun getUserByID(userId: String): User {
        return apiService.getUserByID(userId)
    }
}