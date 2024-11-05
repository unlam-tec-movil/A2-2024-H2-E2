package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed

import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import javax.inject.Inject

class RefreshFeed
    @Inject
    constructor(
        private val tuitRepository: TuitRepository
    ) {
        operator fun invoke() = tuitRepository.getFeed()
    }
