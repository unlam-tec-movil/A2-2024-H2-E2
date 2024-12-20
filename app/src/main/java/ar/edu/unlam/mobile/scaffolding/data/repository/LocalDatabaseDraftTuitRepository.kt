package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.DraftTuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.mapper.DraftTuitMapper
import ar.edu.unlam.mobile.scaffolding.domain.model.DraftTuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.DraftTuitRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDatabaseDraftTuitRepository
    @Inject
    constructor(
        private val draftTuitDao: DraftTuitDao,
        private val draftTuitMapper: DraftTuitMapper,
        private val profileRepository: ProfileRepository,
    ) : DraftTuitRepository {
        override suspend fun getDrafts(): Flow<List<DraftTuit>> {
            val userEmail = profileRepository.getProfile().email
            return draftTuitDao.getDraftTuits(userEmail).map { entities ->
                entities.map { entity ->
                    draftTuitMapper.mapToDomain(entity)
                }
            }
        }

        override suspend fun saveDraft(draftTuit: DraftTuit) {
            val userEmail = profileRepository.getProfile().email
            val entity = draftTuitMapper.mapToEntity(draftTuit.copy(userEmail = userEmail))
            draftTuitDao.saveDraftTuit(entity)
        }

        override suspend fun deleteDraftById(draftTuitId: Int) {
            val userEmail = profileRepository.getProfile().email
            draftTuitDao.deleteDraftTuitByIdAndUser(
                draftId = draftTuitId,
                userEmail = userEmail,
            )
        }
    }
