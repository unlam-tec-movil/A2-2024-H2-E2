package ar.edu.unlam.mobile.scaffolding.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.entity.LocalProfileEntity

@Dao
interface LocalProfileDao {
    @Query("SELECT * FROM profile_table")
    suspend fun getProfile(): LocalProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(entity: LocalProfileEntity)
}
