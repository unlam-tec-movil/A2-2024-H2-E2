package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.creation

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.DraftTuitRepository
import javax.inject.Inject

class RemoveDraftTuit
    @Inject
    constructor(
        private val draftTuitRepository: DraftTuitRepository,
    ) {
        suspend operator fun invoke(draftId: Int) {
            draftTuitRepository.deleteDraftById(draftId)
        }
    }
