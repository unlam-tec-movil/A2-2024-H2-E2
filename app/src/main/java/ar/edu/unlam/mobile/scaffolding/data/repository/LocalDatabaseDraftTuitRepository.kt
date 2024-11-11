package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.DraftTuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.mapper.DraftTuitMapper
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.DraftTuitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDatabaseDraftTuitRepository
    @Inject
    constructor(
        private val draftTuitDao: DraftTuitDao,
        private val draftTuitMapper: DraftTuitMapper,
    ) : DraftTuitRepository {
        override suspend fun getDrafts(): Flow<List<DraftTuit>> {
            val entities = draftTuitDao.getDraftTuits()
            return draftTuitMapper.mapToDomainList(entities)
        }

        override suspend fun saveDraft(draftTuit: DraftTuit) {
            val entity = draftTuitMapper.mapToEntity(draftTuit)
            draftTuitDao.saveDraftTuit(entity)
        }

        override suspend fun deleteDraft(draftTuit: DraftTuit) {
            val entity = draftTuitMapper.mapToEntity(draftTuit)
            draftTuitDao.deleteDraftTuit(entity)
        }
    }
