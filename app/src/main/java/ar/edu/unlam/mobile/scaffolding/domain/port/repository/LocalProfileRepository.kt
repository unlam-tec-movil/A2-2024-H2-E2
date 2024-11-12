package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.Profile

interface LocalProfileRepository {
    suspend fun getLocalProfile(): Profile?

    suspend fun saveLocalProfile(profile: Profile)
}
