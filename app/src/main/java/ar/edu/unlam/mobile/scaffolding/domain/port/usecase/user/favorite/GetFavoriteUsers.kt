package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository
import javax.inject.Inject

data class GetFavoriteUsers
    @Inject
    constructor(
        private val favoriteUserRepository: FavoriteUserRepository,
    ) {
        suspend operator fun invoke() = favoriteUserRepository.getFavoriteUsers()
    }
