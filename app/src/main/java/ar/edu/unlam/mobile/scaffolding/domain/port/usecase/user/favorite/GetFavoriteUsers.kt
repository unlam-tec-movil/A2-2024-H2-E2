package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite

import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class GetFavoriteUsers
    @Inject
    constructor(
        private val favoriteUserRepository: FavoriteUserRepository,
    ) {
        suspend operator fun invoke(): Flow<List<FavoriteUser>> = favoriteUserRepository.getFavoriteUsers()
    }
