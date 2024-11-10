package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import javax.inject.Inject

class CreateTuit
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) {
        suspend operator fun invoke(message: String): Boolean {
            return tuitRepository.createTuit(message)
        }
    }
