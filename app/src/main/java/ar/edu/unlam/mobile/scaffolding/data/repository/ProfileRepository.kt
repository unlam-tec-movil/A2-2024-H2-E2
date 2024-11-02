package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.remote.api.ProfileApi
import javax.inject.Inject

class ProfileRepository
    @Inject
    constructor(
        private val profileApi: ProfileApi,
    ) {
    // TODO: Implementar métodos de perfil
}
