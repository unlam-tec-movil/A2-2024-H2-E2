package ar.edu.unlam.mobile.scaffolding.data.local.mapper

import ar.edu.unlam.mobile.scaffolding.data.local.entity.FavoriteUserEntity
import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteUserMapper
    @Inject
    constructor() {
        fun mapToDomain(favoriteUserEntity: FavoriteUserEntity): FavoriteUser =
            FavoriteUser(
                name = favoriteUserEntity.name,
                avatarUrl = favoriteUserEntity.avatarUrl,
                userEmail = favoriteUserEntity.userEmail,
            )

        fun mapToEntity(favoriteUser: FavoriteUser): FavoriteUserEntity =
            FavoriteUserEntity(
                name = favoriteUser.name,
                avatarUrl = favoriteUser.avatarUrl,
                userEmail = favoriteUser.userEmail,
            )

        fun mapToDomainList(favoriteUserEntities: Flow<List<FavoriteUserEntity>>): Flow<List<FavoriteUser>> =
            favoriteUserEntities.map { entities ->
                entities.map { mapToDomain(it) }
            }
    }
