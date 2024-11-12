package ar.edu.unlam.mobile.scaffolding.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.entity.DraftTuitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftTuitDao {
    @Insert
    suspend fun saveDraftTuit(draft: DraftTuitEntity)

    @Query("SELECT * FROM draft_tuits WHERE userId = :userId")
    fun getDraftTuits(userId: String): Flow<List<DraftTuitEntity>>

    @Query("DELETE FROM draft_tuits WHERE message = :message AND userId = :userId")
    suspend fun deleteDraftTuitByMessage(message: String, userId: String)

    @Delete
    suspend fun deleteDraftTuit(draft: DraftTuitEntity)
}
