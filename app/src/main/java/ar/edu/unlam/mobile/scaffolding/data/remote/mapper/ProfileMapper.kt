package ar.edu.unlam.mobile.scaffolding.data.remote.mapper

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.ProfileResponse
import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import javax.inject.Inject

class ProfileMapper
    @Inject
    constructor() {
        fun toDomain(response: ProfileResponse): Profile =
            Profile(
                name = response.id,
                avatarUrl = response.message,
                email = response.parentId,
            )
    }
