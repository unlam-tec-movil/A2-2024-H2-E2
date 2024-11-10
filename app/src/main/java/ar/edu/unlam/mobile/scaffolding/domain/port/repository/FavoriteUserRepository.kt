package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser

interface FavoriteUserRepository {
    suspend fun savefavoriteUser(user: FavoriteUser)

    suspend fun getFavoriteUsers(): List<FavoriteUser>

    suspend fun deleteFavoriteUser(user: FavoriteUser)
}
