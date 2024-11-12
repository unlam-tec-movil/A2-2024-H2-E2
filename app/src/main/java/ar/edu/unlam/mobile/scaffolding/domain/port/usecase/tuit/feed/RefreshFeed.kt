package ar.edu.unlam.mobile.scaffolding.domain.port.usecase.tuit.feed

import ar.edu.unlam.mobile.scaffolding.domain.model.Tuit
import ar.edu.unlam.mobile.scaffolding.domain.port.repository.TuitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshFeed
    @Inject
    constructor(
        private val tuitRepository: TuitRepository,
    ) {
        operator fun invoke(): Flow<List<Tuit>> = tuitRepository.getFeed()
    }
