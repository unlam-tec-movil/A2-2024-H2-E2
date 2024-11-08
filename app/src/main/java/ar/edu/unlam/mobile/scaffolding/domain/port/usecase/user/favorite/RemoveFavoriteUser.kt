package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite

import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository

data class RemoveFavoriteUser(private val favoriteUserRepository: FavoriteUserRepository) {
    suspend operator fun invoke(user: FavoriteUser) {
        favoriteUserRepository.deleteFavoriteUser(user)
    }
}
