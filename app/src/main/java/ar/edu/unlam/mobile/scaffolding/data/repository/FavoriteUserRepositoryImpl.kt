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
    ) : FavoriteUserRepository{
    override suspend fun savefavoriteUser(user: FavoriteUser) {
        favoriteUserDao.saveFavoriteUser(FavoriteUserMapper.mapToEntity(user))
    }

    override suspend fun getFavoriteUsers(): List<FavoriteUser> {
        return FavoriteUserMapper.mapToDomainList(favoriteUserDao.getFavoriteUsers())
    }

    override suspend fun deleteFavoriteUser(user: FavoriteUser) {
        favoriteUserDao.deleteFavoriteUser(FavoriteUserMapper.mapToEntity(user))
    }
    }
