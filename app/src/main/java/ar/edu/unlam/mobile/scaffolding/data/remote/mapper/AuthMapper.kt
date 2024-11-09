package ar.edu.unlam.mobile.scaffolding.data.remote.mapper

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.UserResponse
import ar.edu.unlam.mobile.scaffolding.domain.model.User
import javax.inject.Inject

class AuthMapper
    @Inject
    constructor() {
        fun toUser(response: UserResponse) =
            User(
                name = response.name,
                email = response.email,
                avatarUrl = response.avatar_url,
            )

        fun toAuthToken(response: UserResponse) = AuthToken(
                token = response.token,
        )

        fun toLoginCredentials(email: String, password: String) = LoginCredentials(
                email = email,
                password = password,
        )
    }
