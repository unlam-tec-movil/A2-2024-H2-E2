package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.favorite

import ar.edu.unlam.mobile.scaffolding.domain.model.FavoriteUser
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.FavoriteUserRepository

data class GetFavoriteUsers (private val favoriteUserRepository: FavoriteUserRepository) {
    suspend operator fun invoke(): List<FavoriteUser> {
        return favoriteUserRepository.getFavoriteUsers()
    }
}
