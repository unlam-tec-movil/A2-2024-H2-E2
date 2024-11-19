package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.FavoriteUserDao
import ar.edu.unlam.mobile.scaffolding.data.local.mapper.FavoriteUserMapper
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDatabaseFavoriteUserRepository
    @Inject
    constructor(
        private val favoriteUserDao: FavoriteUserDao,
        private val favoriteUserMapper: FavoriteUserMapper,
        private val profileRepository: ProfileRepository,
    ) : FavoriteUserRepository {
        override suspend fun savefavoriteUser(user: FavoriteUser) {
            val userEmail = profileRepository.getProfile().email
            val entity = favoriteUserMapper.mapToEntity(user.copy(userEmail = userEmail))
            favoriteUserDao.saveFavoriteUser(entity)
        }

        override suspend fun getFavoriteUsers(): Flow<List<FavoriteUser>> {
            val userEmail = profileRepository.getProfile().email
            val entities = favoriteUserDao.getFavoriteUsers(userEmail)
            return favoriteUserMapper.mapToDomainList(entities)
        }

        override suspend fun deleteFavoriteUser(user: FavoriteUser) {
            val userEmail = profileRepository.getProfile().email
            favoriteUserDao.deleteFavoriteUserByNameAndAvatar(
                name = user.name,
                avatar = user.avatarUrl,
                userEmail = userEmail,
            )
        }
    }
