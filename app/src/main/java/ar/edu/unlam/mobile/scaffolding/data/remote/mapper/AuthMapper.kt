package ar.edu.unlam.mobile.scaffolding.data.remote.mapper

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.UserResponse
import ar.edu.unlam.mobile.scaffolding.domain.model.AuthToken
import javax.inject.Inject

class AuthMapper
    @Inject
    constructor() {
        fun toAuthToken(response: UserResponse) =
            AuthToken(
                token = response.token,
            )
    }
