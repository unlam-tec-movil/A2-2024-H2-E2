package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import kotlinx.coroutines.flow.Flow

interface FavoriteUserRepository {
    suspend fun savefavoriteUser(user: FavoriteUser)

    suspend fun getFavoriteUsers(): Flow<List<FavoriteUser>>

    suspend fun deleteFavoriteUser(user: FavoriteUser)
}
