package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.ProfileRepository
import javax.inject.Inject

class UpdateProfile
    @Inject
    constructor(
        private val profileRepository: ProfileRepository,
    ) {
        suspend operator fun invoke(
            name: String,
            password: String,
            avatarUrl: String,
        ) = profileRepository.updateProfile(name, password, avatarUrl)
    }
