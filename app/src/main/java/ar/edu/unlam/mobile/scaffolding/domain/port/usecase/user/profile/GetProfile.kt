package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile

import ar.edu.unlam.mobile.scaffolding.data.repository.ProfileRepository
import javax.inject.Inject

class GetProfile
    @Inject
    constructor(
        private val profileRepository: ProfileRepository,
    ) {
        suspend operator fun invoke() = profileRepository.getProfile()
    }
