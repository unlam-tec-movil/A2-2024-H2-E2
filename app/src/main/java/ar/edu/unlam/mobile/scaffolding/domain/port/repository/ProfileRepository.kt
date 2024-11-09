package ar.edu.unlam.mobile.scaffolding.domain.port.repository

import ar.edu.unlam.mobile.scaffolding.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile
}