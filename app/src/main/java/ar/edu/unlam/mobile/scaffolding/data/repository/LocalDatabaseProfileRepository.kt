package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.LocalProfileDao
import ar.edu.unlam.mobile.scaffolding.data.local.mapper.LocalProfileMapper
import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.LocalProfileRepository
import javax.inject.Inject

class LocalDatabaseProfileRepository
    @Inject
    constructor(
        private val localProfileDao: LocalProfileDao,
        private val localProfileMapper: LocalProfileMapper
    ) : LocalProfileRepository {

        override suspend fun getLocalProfile(): Profile? {
            val entity = localProfileDao.getProfile()
            return entity?.let { localProfileMapper.mapToDomain(it) }
        }

        override suspend fun saveLocalProfile(profile: Profile) {
            val entity = localProfileMapper.mapToEntity(profile)
            localProfileDao.saveProfile(entity)
        }
    }
