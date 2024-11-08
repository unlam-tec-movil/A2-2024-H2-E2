package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.FavoriteUserDao
import ar.edu.unlam.mobile.scaffolding.data.local.mapper.FavoriteUserMapper
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import javax.inject.Inject

class FavoriteUserRepositoryImpl
    @Inject
    constructor(
        private val favoriteUserDao: FavoriteUserDao,
        private val favoriteUserMapper: FavoriteUserMapper,
    ) : FavoriteUserRepository{
    override suspend fun savefavoriteUser(user: FavoriteUser) {
        val entity = favoriteUserMapper.mapToEntity(user)
        favoriteUserDao.saveFavoriteUser(entity)
    }

    override suspend fun getFavoriteUsers(): List<FavoriteUser> {
        val entities = favoriteUserDao.getFavoriteUsers()
        return favoriteUserMapper.mapToDomainList(entities)
    }

    override suspend fun deleteFavoriteUser(user: FavoriteUser) {
        val entity = favoriteUserMapper.mapToEntity(user)
        favoriteUserDao.deleteFavoriteUser(entity)
    }
    }
