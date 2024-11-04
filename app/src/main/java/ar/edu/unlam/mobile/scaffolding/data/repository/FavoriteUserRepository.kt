package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.dao.FavoriteUserDao
import javax.inject.Inject

class FavoriteUserRepository
    @Inject
    constructor(
        private val favoriteUserDao: FavoriteUserDao,
    ) {
        // TODO: Implementar métodos para usuarios favoritos
    }
