package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.remote.api.ProfileApi
import ar.edu.unlam.mobile.scaffolding.data.remote.mapper.ProfileMapper
import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import javax.inject.Inject

class ProfileRepository
    @Inject
    constructor(
        private val api: ProfileApi,
        private val mapper: ProfileMapper,
    ) {
        suspend fun getProfile(): Profile {
            val response = api.getProfile()
            return mapper.toDomain(response)
        }
    }
