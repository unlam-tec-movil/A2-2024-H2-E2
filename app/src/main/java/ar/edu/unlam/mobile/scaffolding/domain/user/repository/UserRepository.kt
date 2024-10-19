package ar.edu.unlam.mobile.scaffolding.domain.user.repository

import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

interface UserRepository {

    suspend fun login (email: String, password: String): User

    suspend fun createUser (user: User): User

    suspend fun getUserByID (userId: String): User
}