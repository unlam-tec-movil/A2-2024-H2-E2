package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.interaction

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import javax.inject.Inject

class UnLikeTuit
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) {
        suspend operator fun invoke(tuitId: Int) = tuitRepository.unlikeTuit(tuitId)
    }
