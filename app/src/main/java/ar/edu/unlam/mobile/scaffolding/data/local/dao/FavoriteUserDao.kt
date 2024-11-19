package ar.edu.unlam.mobile.scaffolding.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.entity.FavoriteUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteUserDao {
    @Insert
    suspend fun saveFavoriteUser(user: FavoriteUserEntity)

    @Query("SELECT * FROM favorite_users WHERE userEmail = :userEmail")
    fun getFavoriteUsers(userEmail: String): Flow<List<FavoriteUserEntity>>

    @Delete
    suspend fun deleteFavoriteUser(user: FavoriteUserEntity)

    @Query("DELETE FROM favorite_users WHERE name = :name AND avatarUrl = :avatar AND userEmail = :userEmail")
    suspend fun deleteFavoriteUserByNameAndAvatar(
        name: String,
        avatar: String,
        userEmail: String,
    )
}
