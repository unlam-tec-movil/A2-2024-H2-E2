package ar.edu.unlam.mobile.scaffolding.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.entity.FavoriteUserEntity

@Dao
interface FavoriteUserDao {

    @Insert
    suspend fun saveFavoriteUser(user: FavoriteUserEntity)

    @Query("SELECT * FROM favorite_users")
    suspend fun getFavoriteUsers(): List<FavoriteUserEntity>

    @Delete
    suspend fun deleteFavoriteUser(user: FavoriteUserEntity)
}
