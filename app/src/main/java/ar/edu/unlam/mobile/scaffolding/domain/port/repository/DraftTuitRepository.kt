package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import kotlinx.coroutines.flow.Flow

interface DraftTuitRepository {
    suspend fun getDrafts(): Flow<List<DraftTuit>>

    suspend fun saveDraft(draftTuit: DraftTuit)

    suspend fun deleteDraft(draftTuitId: DraftTuit)
}
