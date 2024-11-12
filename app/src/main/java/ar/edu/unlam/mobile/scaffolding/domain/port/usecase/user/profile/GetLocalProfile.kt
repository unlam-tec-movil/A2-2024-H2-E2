package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.user.profile

import ar.edu.unlam.mobile.scaffolding.domain.model.Profile
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.LocalProfileRepository
import javax.inject.Inject

class GetLocalProfile
    @Inject
    constructor(
        private val localProfileRepository: LocalProfileRepository,
    ) {
        suspend operator fun invoke(): Profile? = localProfileRepository.getLocalProfile()
    }