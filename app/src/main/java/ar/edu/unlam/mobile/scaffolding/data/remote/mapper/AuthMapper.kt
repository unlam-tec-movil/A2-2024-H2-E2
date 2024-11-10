package ar.edu.unlam.mobile.scaffolding.data.remote.mapper

import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.ProfileResponse
import ar.edu.unlam.mobile.scaffolding.data.remote.dto.response.UserResponse
import ar.edu.unlam.mobile.scaffolding.domain.model.AuthToken
import ar.edu.unlam.mobile.scaffolding.domain.model.LoginCredentials
import ar.edu.unlam.mobile.scaffolding.domain.model.RegisterCredentials
import ar.edu.unlam.mobile.scaffolding.domain.model.User
import javax.inject.Inject


class AuthMapper
    @Inject
    constructor() {
        fun toUser(response: ProfileResponse) =
            User(
                name = response.name,
                email = response.email,
                avatarUrl = response.avatarUrl,
            )

        fun toAuthToken(response: UserResponse) =
            AuthToken(
                token = response.token,
            )

        fun toLoginCredentials(
            email: String,
            password: String,
        ) = LoginCredentials(
            email = email,
            password = password,
        )

        fun toRegisterCredentials(
            name: String,
            email: String,
            password: String,
        ) = RegisterCredentials(
            name: String,
            email = email,
            password = password,
        )
    }
