package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.DraftTuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.mapper.DraftTuitMapper
import ar.edu.unlam.mobile.scaffolding.data.remote.interceptor.TokenManager
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.DraftTuitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDatabaseDraftTuitRepository @Inject constructor(
    private val draftTuitDao: DraftTuitDao,
    private val draftTuitMapper: DraftTuitMapper,
    private val tokenManager: TokenManager
) : DraftTuitRepository {

    override suspend fun getDrafts(): Flow<List<DraftTuit>> {
        val userId = tokenManager.getToken() ?: ""
        val entities = draftTuitDao.getDraftTuits(userId)
        return draftTuitMapper.mapToDomainList(entities)
    }

    override suspend fun saveDraft(draftTuit: DraftTuit) {
        val userId = tokenManager.getToken() ?: ""
        val entity = draftTuitMapper.mapToEntity(draftTuit.copy(userId = userId))
        draftTuitDao.saveDraftTuit(entity)
    }

    override suspend fun deleteDraft(draftTuit: DraftTuit) {
        val userId = tokenManager.getToken() ?: ""
        draftTuitDao.deleteDraftTuitByMessage(message = draftTuit.message, userId = userId)
    }
}
