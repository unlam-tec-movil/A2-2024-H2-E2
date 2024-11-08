package ar.edu.unlam.mobile.scaffolding.data.local.mapper

import ar.edu.unlam.mobile.scaffolding.data.local.entity.FavoriteUserEntity
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import javax.inject.Inject

class FavoriteUserMapper @Inject constructor()
{
    fun mapToDomain(favoriteUserEntity: FavoriteUserEntity): FavoriteUser {
        return FavoriteUser(
            name = favoriteUserEntity.name,
            avatarUrl = favoriteUserEntity.avatarUrl,
        )
    }

    fun mapToEntity(favoriteUser: FavoriteUser): FavoriteUserEntity {
        return FavoriteUserEntity(
            name = favoriteUser.name,
            avatarUrl = favoriteUser.avatarUrl,
        )
    }

    fun mapToDomainList(favoriteUserEntities: List<FavoriteUserEntity>): List<FavoriteUser> {
        return favoriteUserEntities.map { mapToDomain(it) }
    }

    fun mapToEntityList(favoriteUsers: List<FavoriteUser>): List<FavoriteUserEntity> {
        return favoriteUsers.map { mapToEntity(it) }
    }
}