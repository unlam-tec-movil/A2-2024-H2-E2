package ar.edu.unlam.mobile.scaffolding.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.local.entity.DraftTuitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftTuitDao {
    @Insert
    suspend fun saveDraftTuit(draft: DraftTuitEntity)

    @Query("SELECT * FROM draft_tuits WHERE userEmail = :userEmail")
    fun getDraftTuits(userEmail: String): Flow<List<DraftTuitEntity>>

    @Query("DELETE FROM draft_tuits WHERE id = :draftId AND userEmail = :userEmail")
    suspend fun deleteDraftTuitByIdAndUser(
        draftId: Int,
        userEmail: String,
    )
}
