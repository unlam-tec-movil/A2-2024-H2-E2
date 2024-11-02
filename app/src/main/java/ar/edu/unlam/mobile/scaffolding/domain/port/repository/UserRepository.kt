package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.User

interface UserRepository {
    suspend fun login(email: String, password: String): User
    suspend fun createUser(user: User): User
    suspend fun getUserByID(userId: String): User

    // Ver si faltan métodos
}
